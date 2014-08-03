package com.ankushrayabhari.notifier.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ankushrayabhari.notifier.Notifier;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "UOLTT Notifier";
		config.useGL30 = true;
		config.width = 600;
		config.height = 550;
		config.vSyncEnabled = true;
		config.resizable = false;
		new LwjglApplication(new Notifier(), config);
	}
}
