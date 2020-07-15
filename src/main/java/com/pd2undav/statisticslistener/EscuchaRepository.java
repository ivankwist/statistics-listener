package com.pd2undav.statisticslistener;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EscuchaRepository extends CrudRepository<Escucha, Long>{
    List<Escucha> findByUsuario(String usuario);

    Escucha findById(long id);
}