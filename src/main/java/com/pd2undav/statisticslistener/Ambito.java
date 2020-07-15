package com.pd2undav.statisticslistener;

public class Ambito {
    private String ambito_tipo;
    private String ambito_id;

    public Ambito() {
        super();
    }

    public Ambito(String ambito_tipo, String ambito_id) {
        this.ambito_tipo = ambito_tipo;
        this.ambito_id = ambito_id;
    }

    @Override
    public String toString() {
        return String.format("Ambito[ambito_tipo=%s, ambito_id='%s']", ambito_tipo, ambito_id);
    }

    public String getAmbito_tipo() {
        return ambito_tipo;
    }

    public void setAmbito_tipo(String ambito_tipo) {
        this.ambito_tipo = ambito_tipo;
    }

    public String getAmbito_id() {
        return ambito_id;
    }

    public void setAmbito_id(String ambito_id) {
        this.ambito_id = ambito_id;
    }
}




