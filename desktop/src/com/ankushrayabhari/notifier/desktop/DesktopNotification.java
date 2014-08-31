package com.ankushrayabhari.notifier.desktop;

import com.ankushrayabhari.notifier.Notification;
import com.badlogic.gdx.Gdx;

public class DesktopNotification implements Notification {

	@Override
	public void notify(String message) {
		Gdx.app.log("Notify", "Called");
	}

	@Override
	public void toast(String message, int length) {
		Gdx.app.log("Toast", "Called");
	}

}
