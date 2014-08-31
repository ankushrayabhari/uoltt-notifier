package com.ankushrayabhari.notifier.android;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ankushrayabhari.notifier.Notification;
import com.ankushrayabhari.notifier.Notifier;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication implements Notification {
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Notifier(this), config);
	}
	
	@Override
	public void notify(String message) {
		notification(message);
	}
	
	@Override
	public void toast(String message, int length) {
	    toastSimple(message, length, this);
	}

	public void toastSimple(final String text, final int length, final AndroidLauncher context) {
	    handler.post(new Runnable() {
	    	@Override
	    	public void run() {
	    		Toast.makeText(getApplicationContext(), text,
	    		length).show();
	    	}
	    
	    });
	}
	
	public void notification(String message) {
		android.app.Notification.Builder mBuilder =
		        new android.app.Notification.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("UOLTT Notifier")
		        .setContentText(message);
		NotificationManager mNotificationManager =
			    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			mNotificationManager.notify(1, mBuilder.build());
	}
}
