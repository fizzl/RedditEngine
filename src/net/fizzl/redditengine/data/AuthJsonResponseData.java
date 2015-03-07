package net.fizzl.redditengine.data;

/**
 * Data contents for a reddit api login method response
 * 
 * @see AuthResponse
 */
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
