package net.fizzl.redditengine.data;

public class AuthJsonResponseData {
	private String modhash;
	private String cookie;
	
	public String getModhash() {
		return modhash;
	}
	public void setModhash(String modhash) {
		this.modhash = modhash;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
}
