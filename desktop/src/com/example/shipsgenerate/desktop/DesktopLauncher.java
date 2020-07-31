package com.example.shipsgenerate.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.example.shipsgenerate.Main;
import com.example.shipsgenerate.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Ships";
		config.width = Constants.APP_WIDTH;
		config.height = Constants.APP_HEIGHT;

		new LwjglApplication(new Main(), config);
	}
}
