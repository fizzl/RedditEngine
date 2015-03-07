package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

import com.google.gson.Gson;

import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.JsonResponse;
import net.fizzl.redditengine.data.User;
import net.fizzl.redditengine.data.UserListing;

public class UserApi extends BaseApi {
	/**
	 * Handles friending as well as privilege changes on subreddits.
	 * 
	 * @param user		the name of an existing user
	 * @param container
	 * @param type		one of (<tt>friend, moderator, moderator_invite, contributor, banned, wikibanned, wikicontributor</tt>)
	 * @param permissions
	 * @param note		a string no longer than 300 characters
	 * @return			{@link JsonResponse}
	 * @throws RedditEngineException
	 */
	public JsonResponse<?> friend (String user, String container, String type, String permissions, String note) throws RedditEngineException{
		// POST /api/friend
		String url = String.format("%s/api/friend", UrlUtils.BASE_URL);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_type", "json"));
		params.add(new BasicNameValuePair("name", user));
		if (container != null) {
			params.add(new BasicNameValuePair("container", container));
		}
		params.add(new BasicNameValuePair("type", type));
		if (permissions != null) {
			params.add(new BasicNameValuePair("permissions", permissions));
		}
		if (note != null) {
			params.add(new BasicNameValuePair("note", note));
		}
		
		JsonResponse<?> retval;
		try {
			// response can be:
			// {"json": {"errors": [["USER_REQUIRED", "please login to do that", null]]}}
			// {"json": {"errors": []}}
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			retval = GsonTemplate.fromInputStream(is, JsonResponse.class);
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
		
		return retval;
	}

	/**
	 * Handles removal of a friend (a user-user relation) or removal of a user's privileges from a
	 * subreddit (a user-subreddit relation). The user can either be passed in by name (nuser) or
	 * by fullname (iuser). If type is friend or enemy, 'container' will be the current user,
	 * otherwise the subreddit must be set.
	 * 
	 * @param user		the name of an existing user
	 * @param thingId	fullname of a thing
	 * @param container
	 * @param type		one of (<tt>friend, enemy, moderator, moderator_invite, contributor, banned, wikibanned, wikicontributor</tt>)
	 * @throws RedditEngineException
	 */
	public void unfriend(String user, String thingId, String container, String type) throws RedditEngineException{
		String url = String.format("%s/api/unfriend", UrlUtils.BASE_URL);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", user));
		if (container != null) {
			params.add(new BasicNameValuePair("container", container));
		}
		params.add(new BasicNameValuePair("type", type));
		params.add(new BasicNameValuePair("id", thingId));
		
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			Object response = GsonTemplate.fromInputStream(is, Object.class);
			is.close();
			if (response != null && response instanceof Map && ((Map<?,?>)response).size() > 0) {
				android.util.Log.d(getClass().getName(), new Gson().toJson(response));
			}
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
	}

	public void setPermissions(String user, String subreddit, String permissions, String type) throws RedditEngineException{
		// POST [/r/subreddit]/api/setpermissions
		// hard to test, gives 403
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.BASE_URL);
		if (subreddit != null && !subreddit.isEmpty()) {
			path.append("/r/");
			path.append(subreddit);
		}
		path.append("/api/setpermissions");
		String url = path.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_type", "json"));
		params.add(new BasicNameValuePair("name", user));
		params.add(new BasicNameValuePair("permissions", permissions));
		params.add(new BasicNameValuePair("type", type));
		
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			// if a 403 occurs the response is HTML
			StringWriter writer = new StringWriter();
			IOUtils.copy(is,  writer, "UTF-8");
			is.close();
			String response = writer.toString();
			Pattern pattern = Pattern.compile(".*\\<[^>]+>.*", Pattern.DOTALL);
			boolean containsHTML = pattern.matcher(response).find();
			if (containsHTML == false) {
				// create POJO here
				Log.d(getClass().getName(), response);
			} else {
				Log.e(getClass().getName(), "response was HTML: " + response.substring(0, 64) + "...");
			}
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
	}
	
	/**
	 * Checks if a username is available for registration
	 * 
	 * @param user	username to be checked
	 * @return true or false
	 * @throws RedditEngineException
	 */
	public boolean isUsernameAvailable(String user) throws RedditEngineException{
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.BASE_URL);
		path.append("/api/username_available.json");
		String url = path.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user", user));
		
		SimpleHttpClient client = SimpleHttpClient.getInstance();
		boolean isAvailable = false;
		try {
			InputStream is = client.get(url, params);
			isAvailable = Boolean.valueOf(is.toString());
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return isAvailable;
	}

	/**
	 * Returns information about a user
	 * 
	 * @param user	name of an existing user
	 * @return {@link User}
	 * @throws RedditEngineException
	 */
	public User aboutUser(String user) throws RedditEngineException{
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.BASE_URL);
		path.append("/user/");
		path.append(user);
		path.append("/about.json");
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

	@SuppressWarnings("unused") // TODO use parameters
	public UserListing getUserListing(String username, String where) throws RedditEngineException{
		String path = String.format("/user/%s/%s", username, where);
		String url = UrlUtils.getGetUrl(path);
		
		String show = null;
		String sort = null;
		String t = null;
		String after = null;
		String before = null;
		String count = null;
		String limit = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (username != null) {
			params.add(new BasicNameValuePair("username", username));
		}
		if (show != null) {
			params.add(new BasicNameValuePair("show", show));
		}
		if (sort != null) {
			params.add(new BasicNameValuePair("sort", sort));
		}
		if (t != null) {
			params.add(new BasicNameValuePair("t", t));
		}
		if (after != null) {
			params.add(new BasicNameValuePair("after", after));
		}
		if (before != null) {
			params.add(new BasicNameValuePair("before", before));
		}
		if (count != null) {
			params.add(new BasicNameValuePair("count", count));
		}
		if (limit != null) {
			params.add(new BasicNameValuePair("limit", limit));
		}
				
		UserListing response;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(url, params);
			response = UserListing.fromInputStream(is);
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
		
		return response;
	}

}
