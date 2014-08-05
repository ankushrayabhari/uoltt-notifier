package com.ankushrayabhari.notifier.android;

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
	   public void toast(String message) {
	      toastSimple(message, this);
	   }

	   public void toastSimple(final String text, final AndroidLauncher context) {
	      handler.post(new Runnable() {
	         @Override
	         public void run() {
	            Toast.makeText(getApplicationContext(), text,
	                  Toast.LENGTH_SHORT).show();
	         }
	      });
	   }
}
