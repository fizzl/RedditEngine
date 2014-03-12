package net.fizzl.redditengine.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import net.fizzl.redditengine.data.Listing;
import net.fizzl.redditengine.data.User;

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

	public Listing<?> getUserListing(String user, String what){
		throw new UnimplementedException();
	}

}
