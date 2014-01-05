package net.fizzl.redditengine.engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import net.fizzl.redditengine.RedditApi;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

import android.content.Context;

public class PersistentCookieStore extends BasicCookieStore {
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

	private void load() {
		RedditApi api = new DefaultRedditApi();
		Context ctx = api.getContext();
		try {
			FileInputStream fis = ctx.openFileInput("cookiestore.bin");
			ObjectInputStream ois = new ObjectInputStream(fis);
			BasicCookieStore tempStore = (BasicCookieStore) ois.readObject();
			
			super.clear();
			for(Cookie c : tempStore.getCookies()) {
				super.addCookie(c);
			}
			
			ois.close();
			fis.close();
		} catch(Exception e) {}
	}
	
	private void save() {
		RedditApi api = new DefaultRedditApi();
		Context ctx = api.getContext();
		try {
			FileOutputStream fos = ctx.openFileOutput("cookiestore.bin", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			BasicCookieStore tempStore = new BasicCookieStore();
			for(Cookie c : getCookies()) {
				tempStore.addCookie(c);
			}
			oos.writeObject(tempStore);
			oos.close();
			fos.close();
		} catch (Exception e) {}
	}
}
