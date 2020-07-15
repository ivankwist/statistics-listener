package com.pd2undav.statisticslistener;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Escucha {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String cancion;
    private String ambito_id;
    private String ambito_tipo;
    private String artista;
    private Date fecha;

    protected Escucha() {}

    public Escucha(String usuario, String cancion, String ambito_id, String ambito_tipo) {
        this.usuario = usuario;
        this.cancion = cancion;
        this.ambito_id = ambito_id;
        this.ambito_tipo = ambito_tipo;
        this.artista = null;
        this.fecha = new Date();
    }

    public Escucha(String usuario, String cancion, String ambito_id, String ambito_tipo, String artista) {
        this.usuario = usuario;
        this.cancion = cancion;
        this.ambito_id = ambito_id;
        this.ambito_tipo = ambito_tipo;
        this.artista = artista;
        this.fecha = new Date();
    }

    @Override
    public String toString() {
        return String.format("Escucha[id=%d, usuario=%s, cancion=%s, ambito=%s, artista=%s, fecha=%s]", id, usuario, cancion, ambito_id, artista, fecha.toString());
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCancion() {
        return cancion;
    }

    public String getAmbito_id() {
        return ambito_id;
    }

    public String getArtista() {
        return artista;
    }

    public String getAmbito_tipo() { return ambito_tipo; }

    public Date getFecha() {
        return fecha;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public void setAmbito_id(String ambito_id) {
        this.ambito_id = ambito_id;
    }

    public void setAmbito_tipo(String ambito_tipo) {
        this.ambito_tipo = ambito_tipo;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
