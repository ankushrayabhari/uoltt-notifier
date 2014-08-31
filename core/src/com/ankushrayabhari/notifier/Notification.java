package com.ankushrayabhari.notifier;

public interface Notification {
	public void notify(String message);
	public void toast(String message, int length);
}
