package com.pd2undav.statisticslistener;

public class InsertArtistaMessage {

    private Long escuchaID;
    private String cancionID;

    public InsertArtistaMessage() {}

    public InsertArtistaMessage(Long escuchaID, String cancionID) {
        this.escuchaID = escuchaID;
        this.cancionID = cancionID;
    }

    @Override
    public String toString() {
        return String.format("InsertArtistaMessage[escucha_id=%s, ambito_id='%s]", escuchaID, cancionID);
    }

    public Long getEscuchaID() {
        return escuchaID;
    }

    public void setEscuchaID(Long escuchaID) {
        this.escuchaID = escuchaID;
    }

    public String getCancionID() {
        return cancionID;
    }

    public void setCancionID(String cancionID) {
        this.cancionID = cancionID;
    }
}
