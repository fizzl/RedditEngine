/**
 * Copyright Maxpower Inc Finland (2014)
 *
 * This file is part of RedditEngine.
 *
 * RedditEngine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RedditEngine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with RedditEngine.  If not, see <http://www.gnu.org/licenses/>.
 **/
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