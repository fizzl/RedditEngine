package net.fizzl.redditengine.impl;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import net.fizzl.redditengine.data.Subreddit;
import net.fizzl.redditengine.data.SubredditListing;
import net.fizzl.redditengine.data.SubredditSettings;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonSyntaxException;

import android.util.Log;

/**
 * This class implements subreddits portion of the RedditApi
 * 
 * @see net.fizzl.redditengine.RedditApi
 * @see net.fizzl.redditengine.data.SubredditListing
 */
public class SubredditsApi extends BaseApi {
	public void deleteSubredditHeader(String subreddit){
		throw new UnimplementedException();
	}

	public void deleteSubredditImage(String subreddit, String imageName){
		throw new UnimplementedException();
	}

	public String[] getSubredditRecomendations(String[] reddits, String[] omit){
		throw new UnimplementedException();
	}

	public String[] searchSubreddits(String startswith, boolean withNSFW){
		throw new UnimplementedException();
	}

	public void siteAdmin(String name, SubredditSettings settings){
		throw new UnimplementedException();
	}

	public String getSubredditSubmitText(String subreddit){
		throw new UnimplementedException();
	}

	public String getSubredditStylesheet(String subreddit, String operation, String revid, String contest){
		throw new UnimplementedException();
	}

	public String[] getSubredditsByTopic(String query){
		throw new UnimplementedException();
	}
	
	/**
	 * Handles subscribe and unsubscribe
	 * 
	 * TODO subreddit must be a fullname!
	 * 
	 * @param subreddit		fullname of a subreddit
	 * @param sub			<tt>true</tt> for subscribe, <tt>false</tt> for unsubscribe
	 * @throws RedditEngineException
	 */
	private void subscribeSubreddit(final String subreddit, final boolean sub) throws RedditEngineException {
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.BASE_URL);
		path.append("/api/subscribe");
		String url = path.toString();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String action = (sub == true) ? "sub" : "unsub";
		params.add(new BasicNameValuePair("action", action));
		params.add(new BasicNameValuePair("sr", subreddit));
		params.add(new BasicNameValuePair("api_type", "json"));
		//params.add(new BasicNameValuePair("uh", modhash));
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			//
			StringWriter writer = new StringWriter();
			IOUtils.copy(is, writer, "UTF-8");
			String string = writer.toString();
			Log.d(getClass().getName(), string);
			//response = Subreddit.fromInputStream(is);
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}		
	}

	public void subscribeSubreddit(String subreddit) throws RedditEngineException{
		this.subscribeSubreddit(subreddit, true);
	}

	public void unsubscribeSubreddit(String subreddit) throws RedditEngineException{
		this.subscribeSubreddit(subreddit, false);
	}

	/**
	 * Return information about a subreddit
	 * 
	 * @param subreddit	Subreddit string
	 * @return	Subreddit
	 * @throws RedditEngineException
	 */
	public Subreddit aboutSubreddit(String subreddit) throws RedditEngineException {
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.BASE_URL);
		path.append("/r/");
		path.append(subreddit);
		path.append("/about.json");
		String url = path.toString();
		Subreddit response = null;
		SimpleHttpClient client = SimpleHttpClient.getInstance();
		try {
			InputStream is = client.get(url, null);
			response = Subreddit.fromInputStream(is);
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return response;
	}

	public SubredditSettings getSubredditSettings(String subreddit){
		throw new UnimplementedException();
	}

	/**
	 * Get subreddits the user has a relationship with.
	 * 
	 * <p/><tt>before</tt> and <tt>after</tt> are mutually exclusive.
	 * 
	 * @param where		The where parameter chooses which subreddits are returned as follows: <tt>subscriber</tt> - subreddits the user is subscribed to <tt>contributor</tt> - subreddits the user is an approved submitter in <tt>moderator</tt> - subreddits the user is a moderator of
	 * @param before	fullname of a thing in the listing to use as the anchor point of the slice
	 * @param after		fullname of a thing in the listing to use as the anchor point of the slice
	 * @param count		the number of items already seen in this listing. a positive integer (default: 0)
	 * @param limit		the maximum number of items desired (default: 25, maximum: 100)
	 * @param show		(optional) the string <tt>all</tt>
	 * @return			{@link SubredditListing}
	 * @throws 			RedditEngineException
	 */
	public SubredditListing getMySubreddits(String where, String before, String after, int count, int limit, String show) throws RedditEngineException {
		StringBuilder path = new StringBuilder();
		path.append("/subreddits/mine/");
		path.append(where);
		String url = UrlUtils.getGetUrl(path.toString());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (after != null) {
			params.add(new BasicNameValuePair("after", after));
		} else if (before != null) {
			params.add(new BasicNameValuePair("before", before));
		}
		if(count > 0) {
			params.add(new BasicNameValuePair("count", Integer.toString(count)));
		}
		if(limit > 0) {
			params.add(new BasicNameValuePair("limit", Integer.toString(limit)));
		}
		if(show != null) {
			params.add(new BasicNameValuePair("show", show));
		}
		
		SimpleHttpClient client = SimpleHttpClient.getInstance();
		SubredditListing ret = null;
		try {
			InputStream is = client.get(url, params);
			ret = SubredditListing.fromInputStream(is);
			is.close();
		} catch (JsonSyntaxException e) {
			// TODO SubredditListing should handle empty response {}
			// server returned nothing
			Log.e(getClass().getName(), e.getMessage());
			ret = new SubredditListing();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return ret;
	}

	public SubredditListing searchSubreddits(String query, String before, String after, int count, int limit, String show){
		throw new UnimplementedException();
	}

	/**
	 * Get all subreddits.
	 * 
	 * <p/><tt>before</tt> and <tt>after</tt> are mutually exclusive.
	 *  
	 * @param which		This parameter chooses the order in which the subreddits are displayed. <tt>popular</tt> sorts on the activity of the subreddit and the position of the subreddits can shift around. <tt>new</tt> sorts the subreddits based on their creation date, newest first.
	 * @param before	fullname of a thing in the listing to use as the anchor point of the slice
	 * @param after		fullname of a thing in the listing to use as the anchor point of the slice
	 * @param count		the number of items already seen in this listing. a positive integer (default: 0)
	 * @param limit		the maximum number of items desired (default: 25, maximum: 100)
	 * @param show		(optional) the string <tt>all</tt>
	 * @return			{@link SubredditListing}
	 * @throws 			RedditEngineException
	 */
	public SubredditListing getSubreddits(String which, String before, String after, int count, int limit, String show) throws RedditEngineException{
		StringBuilder path = new StringBuilder();
		path.append("/subreddits/");
		path.append(which);
		String url = UrlUtils.getGetUrl(path.toString());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (after != null) {
			params.add(new BasicNameValuePair("after", after));
		} else if (before != null) {
			params.add(new BasicNameValuePair("before", before));
		}
		if(count > 0) {
			params.add(new BasicNameValuePair("count", Integer.toString(count)));
		}
		if(limit > 0) {
			params.add(new BasicNameValuePair("limit", Integer.toString(limit)));
		}
		if(show != null) {
			params.add(new BasicNameValuePair("show", show));
		}
		
		SimpleHttpClient client = SimpleHttpClient.getInstance();
		SubredditListing ret = null;
		try {
			InputStream is = client.get(url, params);
			ret = SubredditListing.fromInputStream(is);
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return ret;
	}
}
