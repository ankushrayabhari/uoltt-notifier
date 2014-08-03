package com.ankushrayabhari.notifier;

public class Preferences {
	private boolean ltt, csf, tq;
	private String username, password;
	
	public boolean isLtt() {
		return ltt;
	}
	public boolean isCsf() {
		return csf;
	}
	public boolean isTq() {
		return tq;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setLtt(boolean b) {
		ltt = b;
	}
	public void setCsf(boolean b) {
		csf = b;
	}
	public void setTq(boolean b) {
		tq = b;
	}
	public void setUsername(String u) {
		this.username = u;
	}
	public void setPassword(String p) {
		this.password = p;
	}
}
