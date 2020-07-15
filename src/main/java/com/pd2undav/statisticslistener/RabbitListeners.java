package com.pd2undav.statisticslistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.pd2undav.statisticslistener.RabbitConfig.SPUTNIKFY_EXCHANGE;

@Service
public class RabbitListeners {

    private static final Logger log = LoggerFactory.getLogger(RabbitListeners.class);

    @Value("${MUSIC_ADDRESS}")
    private String MUSIC_ADDRESS;
    @Value("${MUSIC_PORT}")
    private String MUSIC_PORT;

    private final EscuchaRepository escuchaRepository;
    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate;

    public RabbitListeners(EscuchaRepository escuchaRepository, RabbitTemplate rabbitTemplate, RestTemplate restTemplate) {
        this.escuchaRepository = escuchaRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.restTemplate = restTemplate;
    }

    @RabbitListener(queues = RabbitConfig.ESCUCHAS_QUEUE)
    public void receiveEscucha(final EscuchaMessage escuchaMessage) {
        log.info("Received escuchaMessage: {}", escuchaMessage.toString());

        Escucha escucha = new Escucha(escuchaMessage.getUsuario(),
                                      escuchaMessage.getCancion(),
                                      escuchaMessage.getAmbito().getAmbito_id(),
                                      escuchaMessage.getAmbito().getAmbito_tipo());
        escuchaRepository.save(escucha);

        Long escuchaID = escucha.getId();
        InsertArtistaMessage message = new InsertArtistaMessage(escuchaID, escucha.getCancion());
        rabbitTemplate.convertAndSend(SPUTNIKFY_EXCHANGE, "escucha.artista", message);

        log.info("Saved escucha: {}", escucha.toString());
    }

    @RabbitListener(queues = RabbitConfig.INSERT_ARTISTA_QUEUE)
    public void receiveInsertArtista(final InsertArtistaMessage insertArtistaMessage) {
        log.info("Received insertArtistaMessage: {}", insertArtistaMessage.toString());

        String url = String.format("http://%s:%s/cancion/artista/id?cancionID=%s", MUSIC_ADDRESS, MUSIC_PORT, insertArtistaMessage.getCancionID());
        String artistaID = restTemplate.getForObject(url, String.class);

        Escucha escucha = escuchaRepository.findById(insertArtistaMessage.getEscuchaID()).orElse(null);
        escucha.setArtista(artistaID);
        escuchaRepository.save(escucha);

        log.info("Set Artista({}) for Escucha: {}", artistaID, escucha.getId());
    }


}
