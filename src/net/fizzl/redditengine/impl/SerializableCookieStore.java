package net.fizzl.redditengine.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.cookie.Cookie;

/**
 * TODO later version of BasicClientCookie implements Serializable, use that
 * @see SerializableCookie
 */
public class SerializableCookieStore implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SerializableCookie> cookies = new ArrayList<SerializableCookie>();
	
	public List<Cookie> getCookies() {
		List<Cookie> ret = new ArrayList<Cookie>();
		for (SerializableCookie serial : cookies) {
			Cookie cookie = (Cookie)serial;
			ret.add(cookie);
		}
		return ret;
	}
	public void addCookie(Cookie cookie) {
		SerializableCookie serial = new SerializableCookie(cookie);
		cookies.add(serial);
	}
}