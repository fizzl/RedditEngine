package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.net.http.AndroidHttpClient;

public class SimpleHttpClient {
	// API
	public InputStream get(String url, List<NameValuePair> params) throws ClientProtocolException, IOException, UnexpectedHttpResponseException {
		if(params != null) {
			String strp = URLEncodedUtils.format(params, "UTF-8");
			url += "?" + strp;
		}
		HttpGet get = new HttpGet(url);
		HttpResponse response = mClient.execute(get, mHttpContext);
		StatusLine line = response.getStatusLine();
		if(line.getStatusCode() != HttpStatus.SC_OK) {
			String msg = String.format("Unexpected return code %s", line.getReasonPhrase());
			UnexpectedHttpResponseException ex = new UnexpectedHttpResponseException(msg);
			throw ex;
		}
		return response.getEntity().getContent();
	}
	
	public InputStream post(String url, List<NameValuePair> params) throws ClientProtocolException, IOException, UnexpectedHttpResponseException {
		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(params));
		HttpResponse response = mClient.execute(post, mHttpContext);
		StatusLine line = response.getStatusLine();
		if(line.getStatusCode() != HttpStatus.SC_OK) {
			String msg = String.format("Unexpected return code %s", line.getReasonPhrase());
			UnexpectedHttpResponseException ex = new UnexpectedHttpResponseException(msg);
			throw ex;
		}
		
		return response.getEntity().getContent();
	}
	
	// Singleton
	private SimpleHttpClient() {
		mClient = AndroidHttpClient.newInstance(USER_AGENT);
		mCookieStore = new PersistentCookieStore();
		mHttpContext = new BasicHttpContext();
		mHttpContext.setAttribute(ClientContext.COOKIE_STORE, mCookieStore);
	}

	public static SimpleHttpClient getInstance() {
		if(me == null) {
			me = new SimpleHttpClient();
		}
		return me;
	}
	private static SimpleHttpClient me;

	// Local variables
	private HttpClient mClient;
	private HttpContext mHttpContext;
	private CookieStore mCookieStore;
	
	// Constants
	private static final String USER_AGENT = "fizzl.net/RedditEngine/SimpleHttpClient 1.0/by fizzl";
}
