package com.mygdx.game.Enum;

/**
 *@author Blas Machado
 * @version 1.0
 */
public enum Direcciones {


    //TODO: Cambiar nombres

    LOGO_UTN("Logos/logoUTN.png"),
    LOGO_UTN2("Logos/logoUTN2.png"),
    LOGO_PEDIDOS_YA("Logos/logoPEDIDOSYA.png"),
    BACKGROUND_MENU("Botones/menu.png"),
    BACKGROUND_SCOREBOARD("Botones/scoreBoardMenu.png"),
    BACKGROUND_GAMEOVER("Botones/gameOverMenu.png"),
    BACKGROUND_WIN("Botones/YouWinMenu.png"),
    MAPA_TMX("Map/MarioMap.tmx"),
    BOTON_PLAYGAME("Botones/startButton.png"),
    BOTON_QUITGAME("Botones/quitButton.png"),
    BOTON_SOCRE("Botones/scoreButton.png"),
    BOTON_SAVE("Botones/saveButton.png"),
    FILE_SCORE("proyecto2/JSON/score.json");

//FILE_SCORE("JSON/score.json");


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
