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
		RedditApi api = DefaultRedditApi.newInstance();
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
		RedditApi api = DefaultRedditApi.newInstance();
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
