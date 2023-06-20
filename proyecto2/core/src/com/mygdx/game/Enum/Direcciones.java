package com.mygdx.game.Enum;

public enum Direcciones {


    LOGO_UTN("Logos/logoUTN.png"),
    LOGO_UTN2("Logos/logoUTN2.png"),
    LOGO_PEDIDOS_YA("Logos/logoPEDIDOSYA.png"),
    MAPA_TMX("Map/MarioMap.tmx"),
    BOTON_PLAYGAME("Botones/startButton.png"),
    BOTON_QUITGAME("Botones/quitButton.png");



    private String filePath;

    Direcciones(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
