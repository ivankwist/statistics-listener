package com.pd2undav.statisticslistener;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfig {
    // Exchanges
    public static final String SPUTNIKFY_EXCHANGE = "sputnikfy-exchange";

    @Bean
    public TopicExchange sputnikfyExchange() {
        return new TopicExchange(SPUTNIKFY_EXCHANGE);
    }

    // Queues
    public static final String ESCUCHAS_QUEUE = "escuchas";

    @Bean
    public Queue escuchasQueue() {
        return QueueBuilder.durable(ESCUCHAS_QUEUE).quorum().build();
    }

    public static final String INSERT_ARTISTA_QUEUE = "insert-artista";

    @Bean
    public Queue insertArtistaQueue() {
        return QueueBuilder.durable(INSERT_ARTISTA_QUEUE).quorum().build();
    }

    // Bindings
    @Bean
    public Binding escuchasBinding() {
        return BindingBuilder.bind(escuchasQueue()).to(sputnikfyExchange()).with("actividad.escucha");
    }

    @Bean
    public Binding insertArtistaBinding() {
        return BindingBuilder.bind(insertArtistaQueue()).to(sputnikfyExchange()).with("escucha.artista");
    }

    // Converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
