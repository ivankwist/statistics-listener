package com.pd2undav.statisticslistener;

public class EscuchaMessage {

    private String usuario;
    private String cancion;
    private Ambito ambito;

    public EscuchaMessage(String usuario, String cancion, Ambito ambito) {
        this.usuario = usuario;
        this.cancion = cancion;
        this.ambito = ambito;
    }

    public EscuchaMessage() {}

    @Override
    public String toString() {
        return String.format("EscuchaMessage[usuario=%s, cancion='%s', ambito='%s']", usuario, cancion, ambito.toString());
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCancion() {
        return cancion;
    }

    public Ambito getAmbito() {
        return ambito;
    }
}
