package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Enum.Direcciones;


public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(1200,624);
		config.setWindowIcon(Direcciones.LOGO_UTN2.getFilePath());
		config.setTitle("PROYECTO UTN");
		new Lwjgl3Application(new DeliveryBros(), config);
	}
}
