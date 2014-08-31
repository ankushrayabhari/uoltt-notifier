package com.ankushrayabhari.notifier;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSDate;
import org.robovm.apple.foundation.NSDateFormatter;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UILocalNotification;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

public class IOSLauncher extends IOSApplication.Delegate implements Notification {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.orientationPortrait = false;
        config.orientationLandscape = true;
        return new IOSApplication(new Notifier(this), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }

	@Override
	public void notify(String message) {
		UILocalNotification notification = new UILocalNotification();
		NSDateFormatter dateFormat = new NSDateFormatter();
		
		notification.setFireDate(dateFormat.to);
		notification.setAlertBody(message);
		UIApplication.getSharedApplication().scheduleLocalNotification(notification);
	}

	@Override
	public void toast(String message, int length) {
		notify(message);
	}
}