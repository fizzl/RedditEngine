package net.fizzl.redditengine.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import net.fizzl.redditengine.data.AuthResponse;
import net.fizzl.redditengine.data.User;

public class AccountApi extends BaseApi {
	private static final String REDDIT_SSL = "https://ssl.reddit.com";
	private static final String LOGIN_PATH = "/api/login";
	private static final String API_TYPE_JSON = "json";
	
	public void clearSessions(String passwd) throws RedditEngineException {
		StringBuilder path = new StringBuilder();
		path.append(REDDIT_SSL);
		path.append("/api/clear_sessions");
		String url = path.toString();		
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_type", API_TYPE_JSON));
		params.add(new BasicNameValuePair("curpass", passwd));
		
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(url, null);
			// TODO suitable response class
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return;
	}
	
	public void deleteUser(String user, String passwd, String message)  {
		throw new UnimplementedException();
	}
	
	/**
	 * Log into a reddit account using SSL
	 * 
	 * @param user		username
	 * @param passwd	users password
	 * @param remember	specifies whether or not the session cookie returned should last beyond the current browser session 
	 * @throws RedditEngineException
	 */
	public AuthResponse login(String user, String passwd, boolean remember) throws RedditEngineException  {
		StringBuilder sb = new StringBuilder();
		sb.append(REDDIT_SSL);
		sb.append(LOGIN_PATH);
		String url = sb.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user", user));
		params.add(new BasicNameValuePair("passwd", passwd));
		params.add(new BasicNameValuePair("api_type", API_TYPE_JSON));
		params.add(new BasicNameValuePair("rem", Boolean.toString(remember)));
		
		// The HTTP status code of the response will always be a 200 (OK) regardless of authentication success.
		// To see if login was successful, check the JSON response.
		
		AuthResponse ret = null;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			Log.i(AccountApi.class.getCanonicalName(), is.toString());
			ret = AuthResponse.fromInputStream(is);
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return ret;
	}

	/**
	 * Get info about the currently authenticated user
	 * 
	 * @return {@link User}
	 * @throws RedditEngineException
	 */
	public User me() throws RedditEngineException  {
		StringBuilder path = new StringBuilder();
		path.append(REDDIT_SSL);
		path.append("/api/me.json");
		String url = path.toString();
		User response = new User();
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(url, null);
			response = User.fromInputStream(is);
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return response;
	}

	public void register(String user, String passwd1, String passwd2, boolean remember, String email, String captcha, String captcha_iden)  {
		throw new UnimplementedException();
	}

	public void updateUser(String passwd, String email, String newpass1, String newpass2, boolean verify)  {
		throw new UnimplementedException();
	}
}
