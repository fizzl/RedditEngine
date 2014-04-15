package net.fizzl.redditengine.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;
import net.fizzl.redditengine.data.AuthResponse;
import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.JsonResponse;
import net.fizzl.redditengine.data.User;

public class AccountApi extends BaseApi {
	private static final String LOGIN_PATH = "/api/login";
	private static final String API_TYPE_JSON = "json";
	
	/**
	 * Clear all session cookies and replace the current one.
	 * 
	 * @param passwd	the user's current password
	 * @return			errors if unsuccessful
	 * @throws RedditEngineException
	 */
	public JsonResponse<?> clearSessions(String passwd) throws RedditEngineException {
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.REDDIT_SSL);
		path.append("/api/clear_sessions");
		String url = path.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_type", API_TYPE_JSON));
		
		if (passwd != null && !passwd.isEmpty()) {
			params.add(new BasicNameValuePair("curpass", passwd));			
		}
		else if (lastPassword != null) {
			params.add(new BasicNameValuePair("curpass", lastPassword));
		}
		// TODO destination url parameter "dest"
		// TODO should manually clear session cookies?
		
		JsonResponse<?> response = new AuthResponse();  // AuthResponse is a temporary placeholder to get data into JsonReponse. TODO should not depend on AuthResponse
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			response = AuthResponse.fromInputStream(is);
			Log.d(getClass().getName(), new com.google.gson.Gson().toJson(response));
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return response;
	}
	
	/**
	 * Delete the currently logged in account.
	 * 
	 * @param user		a username
	 * @param passwd	the user's password
	 * @param message	a string no longer than 500 characters
	 * @return {@link JsonResponse}
	 * @throws RedditEngineException
	 */
	public JsonResponse<?> deleteUser(String user, String passwd, String message) throws RedditEngineException  {
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.REDDIT_SSL);
		path.append("/api/delete_user");
		String url = path.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_type", API_TYPE_JSON));		
		params.add(new BasicNameValuePair("user", user));
		if (passwd != null) {
			params.add(new BasicNameValuePair("passwd", passwd));
		}
		if (message != null) {
			params.add(new BasicNameValuePair("delete_message", message));
		}
		params.add(new BasicNameValuePair("confirm", String.valueOf(true)));
		
		// possible responses:
		// {"json": {"errors": [["USER_REQUIRED", "please login to do that", null]]}}
		// {"json": {"errors": []}}
		JsonResponse<?> retval;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			retval = GsonTemplate.fromInputStream(is, JsonResponse.class);
			is.close();
		} catch (Exception e) {
			throw new RedditEngineException(e);
		}
		
		return retval;
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
		sb.append(UrlUtils.REDDIT_SSL);
		sb.append(LOGIN_PATH);
		String url = sb.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user", user));
		params.add(new BasicNameValuePair("passwd", passwd));
		params.add(new BasicNameValuePair("api_type", API_TYPE_JSON));
		params.add(new BasicNameValuePair("rem", Boolean.toString(remember)));
		
		// The HTTP status code of the response will almost always be a 200 (OK) regardless of authentication success.
		// To see if login was successful, check the JSON response.
		// POST requests to /api/login must not include a reddit_session cookie along in the request. If a reddit_session cookie exists, the request may fail with a 409 status.
		
		AuthResponse ret = null;
		SimpleHttpClient client = SimpleHttpClient.getInstance();
		// TODO should SimpleHttpClient have some kind of save/restore cookies function instead of accessing the cookiestore directly?
		BasicCookieStore cookieStore = client.getCookieStore();
		List<Cookie> cookies = cookieStore.getCookies();
		Cookie[] cookieArray = cookies.toArray(new Cookie[cookies.size()]);
		try {
			cookieStore.clear();
			InputStream is = client.post(url, params);
			ret = AuthResponse.fromInputStream(is);
			if (ret.getJson().getData() != null) {
				lastPassword = passwd;
			} else {
				// an error occurred
				cookieStore.addCookies(cookieArray);
			}
			is.close();
		} catch (Exception e) {
			// if failed, put the cookies back
			cookieStore.addCookies(cookieArray);
			throw new RedditEngineException(e);
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
		path.append(UrlUtils.REDDIT_SSL);
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

	/**
	 * Register a new user.
	 * 
	 * @param user			a valid, unused, username
	 * @param passwd1		password for new user
	 * @param passwd2		password for new user (again)
	 * @param remember		specifies whether or not the session cookie returned should last beyond the current browser session 
	 * @param email			(optional) the user's email address
	 * @param captcha		the user's response to the CAPTCHA challenge
	 * @param captchaIden	the identifier of the CAPTCHA challenge
	 * @return {@link AuthResponse}
	 * @throws RedditEngineException
	 */
	public AuthResponse register(String user, String passwd1, String passwd2, boolean remember, String email, String captcha, String captcha_iden) throws RedditEngineException  {
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.REDDIT_SSL);
		path.append("/api/register");
		String url = path.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_type", API_TYPE_JSON));		
		params.add(new BasicNameValuePair("user", user));
		params.add(new BasicNameValuePair("passwd", passwd1));
		params.add(new BasicNameValuePair("passwd2", passwd2));
		if (captcha != null) {
			params.add(new BasicNameValuePair("captcha", captcha));
		}
		if (captcha_iden != null) {
			params.add(new BasicNameValuePair("iden", captcha_iden));
		}
		params.add(new BasicNameValuePair("rem", String.valueOf(remember)));
		if (email != null) {
			params.add(new BasicNameValuePair("email", email));
		}
		
		AuthResponse retval;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			retval = AuthResponse.fromInputStream(is);
			is.close();
		} catch (Exception e) {
			throw new RedditEngineException(e);
		}
		return retval;
	}

	/**
	 * Update account email address and password.
	 * newpass1 and newpass2 must be equal for a password change to succeed.
	 * 
	 * @param passwd	the current password	
	 * @param email
	 * @param newpass1	the new password
	 * @param newpass2	the password again (for verification)
	 * @param verify	boolean value
	 * @return {@link JsonResponse}
	 * @throws RedditEngineException 
	 */
	public JsonResponse<?> updateUser(String passwd, String email, String newpass1, String newpass2, boolean verify) throws RedditEngineException  {
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.REDDIT_SSL);
		path.append("/api/update");
		String url = path.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_type", API_TYPE_JSON));		
		if (passwd != null) {
			params.add(new BasicNameValuePair("curpass", passwd));
		}
		if (email != null) {
			params.add(new BasicNameValuePair("email", email));
		}
		if (newpass1 != null) {
			params.add(new BasicNameValuePair("newpass", newpass1));
		}
		if (newpass2 != null) {
			params.add(new BasicNameValuePair("verpass", newpass2));
		}
		params.add(new BasicNameValuePair("verify", String.valueOf(verify)));
		
		// response can be:
		// {"json": {"errors": [["WRONG_PASSWORD", "invalid password", "curpass"]]}}
		// {"json": {"errors": []}}
		JsonResponse<?> retval;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			retval = GsonTemplate.fromInputStream(is, JsonResponse.class);
			is.close();
		} catch (Exception e) {
			throw new RedditEngineException(e);
		}
		
		return retval;
	}
	
	private String lastPassword;  // last known password the user has entered
}
