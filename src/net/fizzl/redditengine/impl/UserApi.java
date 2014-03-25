package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import net.fizzl.redditengine.data.User;
import net.fizzl.redditengine.data.UserListing;

public class UserApi extends BaseApi {
	public void friend(String user, String container, String type, String permissions, String note){
		throw new UnimplementedException();
	}

	public void unfriend(String user, String thingId, String container, String type){
		throw new UnimplementedException();
	}

	public void setPermissions(String user, String subreddit, String permissions){
		throw new UnimplementedException();
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
