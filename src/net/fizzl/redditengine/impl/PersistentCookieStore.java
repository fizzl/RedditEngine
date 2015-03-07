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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import net.fizzl.redditengine.RedditApi;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

import android.content.Context;
import android.util.Log;

/**
 * This class persists in-memory cookies
 * 
 * @see SerializableCookieStore
 */
public class PersistentCookieStore extends BasicCookieStore {
	private static final String cookiestore = "cookiestore.bin";
	
	public PersistentCookieStore() {
		super();
		load();
	}

	@Override
	public void addCookie(Cookie cookie) {
		super.addCookie(cookie);
		save();
	}
	
	@Override
	public void clear() {
		super.clear();
		save();
	}
	
	@Override
	public boolean clearExpired(Date date) {
		boolean ret = super.clearExpired(date);
		save();
		return ret;
	}
	
	/**
	 * Load Cookies from a file
	 */
	private void load() {
		RedditApi api = DefaultRedditApi.getInstance();
		Context ctx = api.getContext();
		try {
			FileInputStream fis = ctx.openFileInput(cookiestore);
			ObjectInputStream ois = new ObjectInputStream(fis);
			SerializableCookieStore tempStore = (SerializableCookieStore) ois.readObject();
			
			super.clear();
			for(Cookie c : tempStore.getCookies()) {
				super.addCookie(c);
			}
			
			ois.close();
			fis.close();
		} catch(FileNotFoundException e) {
			Log.w(getClass().getName(), e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save cookies to a file
	 */
	private void save() {
		RedditApi api = DefaultRedditApi.getInstance();
		Context ctx = api.getContext();
		try {
			FileOutputStream fos = ctx.openFileOutput(cookiestore, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			SerializableCookieStore tempStore = new SerializableCookieStore();
			for(Cookie c : getCookies()) {
				tempStore.addCookie(c);
			}
			oos.writeObject(tempStore);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
}
