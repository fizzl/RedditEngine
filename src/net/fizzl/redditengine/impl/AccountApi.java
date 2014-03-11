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
	private static final String LOGIN_URL = "https://ssl.reddit.com/api/login";  // use HTTPS/SSL instead of HTTP
	
	public void clearSessions(String passwd) {
		throw new UnimplementedException();
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
		sb.append(LOGIN_URL);
		//sb.append("/");
		//sb.append(user);
		String url = sb.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user", user));
		params.add(new BasicNameValuePair("passwd", passwd));
		params.add(new BasicNameValuePair("api_type", "json"));
		params.add(new BasicNameValuePair("rem", Boolean.toString(remember)));
		
		// The HTTP status code of the response will always be a 200 (OK) regardless of authentication success.
		// To see if login was successful, check the JSON response.
		
		SimpleHttpClient client = SimpleHttpClient.getInstance();
		AuthResponse ret = null;
		try {
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

	public User me()  {
		throw new UnimplementedException();
	}

	public void register(String user, String passwd1, String passwd2, boolean remember, String email, String captcha, String captcha_iden)  {
		throw new UnimplementedException();
	}

	public void updateUser(String passwd, String email, String newpass1, String newpass2, boolean verify)  {
		throw new UnimplementedException();
	}
}
