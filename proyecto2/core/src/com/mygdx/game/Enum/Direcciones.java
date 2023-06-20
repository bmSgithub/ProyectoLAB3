package com.mygdx.game.Enum;

public enum Direcciones {


    LOGO_UTN("Otros/logoUTN.png"),
    LOGO_PEDIDOS_YA("Otros/logoPEDIDOSYA.png"),
    MAPA_TMX("Map/MarioMap.tmx"),
    BOTON_PLAYGAME("Boton/startButton.png"),
    BOTON_QUITGAME("Boton/quitButton.png"),
    FUENTE_GAME("Fuentes/Super_Comic.ttf"),
    MENU_BACKGOUNRD("Boton/backgroundMenu.png");


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
