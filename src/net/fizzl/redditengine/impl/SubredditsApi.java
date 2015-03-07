package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.ListMapValue;
import net.fizzl.redditengine.data.Subreddit;
import net.fizzl.redditengine.data.SubredditData;
import net.fizzl.redditengine.data.SubredditListing;
import net.fizzl.redditengine.data.SubredditSettings;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URLEncodedUtils;
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

	/**
	 * Return subreddits recommended for the given subreddit(s).
	 * 
	 * @param reddits	subreddit names
	 * @param omits		subreddit names to be filtered out
	 * @return list of subreddits
	 * @throws RedditEngineException
	 */
	public String[] getSubredditRecomendations(String[] reddits, String[] omits) throws RedditEngineException{
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.BASE_URL);
		path.append("/api/recommend/sr/");
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (reddits != null) {
			params.add(new BasicNameValuePair("srnames", UrlUtils.toCommaDelimited(reddits)));
		}
		String strp = URLEncodedUtils.format(params, "UTF-8");
		path.append(strp);
		path.append(".json");
		String url = path.toString();
		
		List<NameValuePair> optional;
		if (omits == null) {
			optional = null;
		} else {
			optional = new ArrayList<NameValuePair>();
			optional.add(new BasicNameValuePair("omit", UrlUtils.toCommaDelimited(omits)));
		}
		
		List<String> retval = new ArrayList<String>();
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(url, optional);
			retval = ListMapValue.fromInputStream(is, "sr_name");
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		
		return retval.toArray(new String[retval.size()]);
	}
	
	/**
	 * List subreddit names that begin with a query string
	 * 
	 * @param startswith	a string up to 50 characters long, consisting of printable characters
	 * @param withNSFW		boolean value
	 * @return				subreddit names
	 * @throws RedditEngineException 
	 */
	public String[] searchSubreddits(String startswith, boolean withNSFW) throws RedditEngineException{
		StringBuilder path = new StringBuilder();
		path.append("/api/search_reddit_names");
		String url = UrlUtils.getGetUrl(path.toString());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (startswith != null) {
			params.add(new BasicNameValuePair("query", startswith));
		}
		params.add(new BasicNameValuePair("include_over_18", String.valueOf(withNSFW)));

		List<String> names = new ArrayList<String>();
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream in = client.post(url, params);
			// TODO make a wrapper class from this?
			Map<String, List<String>> maplist = GsonTemplate.fromInputStream(in, Map.class);	
			in.close();
			names = maplist.get("names");
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
		
		return names.toArray(new String[names.size()]);
	}

	public void siteAdmin(String name, SubredditSettings settings){
		throw new UnimplementedException();
	}

	public String getSubredditSubmitText(String subreddit) throws RedditEngineException {
		StringBuilder path = new StringBuilder();
		path.append("/r/");
		path.append(subreddit);
		path.append("/api/submit_text");		
		String url = UrlUtils.getGetUrl(path.toString());
		// response json has "submit_text", "submit_text_html" which can be found in SubredditData
		SubredditData response = new SubredditData();
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(url, null);
			response = GsonTemplate.fromInputStream(is, SubredditData.class);
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
		return response.getSubmit_text();
	}

	public String getSubredditStylesheet(String subreddit, String operation, String revid, String contest){
		throw new UnimplementedException();
	}

	/**
	 * Return a list of subreddits that are relevant to a search query
	 * 
	 * @param 	query	a string no longer than 50 characters
	 * @return	list of subreddits
	 * @throws RedditEngineException 
	 */
	public String[] getSubredditsByTopic(String query) throws RedditEngineException{
		StringBuilder place = new StringBuilder();
		place.append("/api/subreddits_by_topic");
		String url = UrlUtils.getGetUrl(place.toString());

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (query != null) {
			params.add(new BasicNameValuePair("query", query));
		}
		
		List<String> retval = new ArrayList<String>();
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(url, params);
			retval = ListMapValue.fromInputStream(is, "name");
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		
		return retval.toArray(new String[retval.size()]);
	}
	
	/**
	 * Handles subscribe and unsubscribe
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
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			StringWriter writer = new StringWriter();
			IOUtils.copy(is, writer, "UTF-8");
			String string = writer.toString();
			Log.d(getClass().getName() + "." + "subscribeSubreddit() got ", string);
			//response = Subreddit.fromInputStream(is);
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}		
	}

	/**
	 * Subscribe to a subreddit
	 * 
	 * @param subreddit	fullname of a subreddit 
	 * @throws RedditEngineException
	 */
	public void subscribeSubreddit(String subreddit) throws RedditEngineException{
		this.subscribeSubreddit(subreddit, true);
	}

	/**
	 * Unsubscribe from a subreddit

	 * @param subreddit	fullname of a subreddit
	 * @throws RedditEngineException
	 */
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
		//GET /r/<subreddit>/about/edit.json just returns 404, broken?
		//String url = String.format("%s/r/%s/about/edit.json", UrlUtils.BASE_URL, subreddit);
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
		path.append("mine/");
		path.append(where);
		String which = path.toString();
		return getSubreddits(which, before, after, count, limit, show);
	}

	/**
	 * Search subreddits by title and description.
	 * 
	 * <p/><tt>before</tt> and <tt>after</tt> are mutually exclusive.
	 * 
	 * @param query		a search query
	 * @param before	fullname of a thing in the listing to use as the anchor point of the slice
	 * @param after		fullname of a thing in the listing to use as the anchor point of the slice
	 * @param count		the number of items already seen in this listing. a positive integer (default: 0)
	 * @param limit		the maximum number of items desired (default: 25, maximum: 100)
	 * @param show		(optional) the string <tt>all</tt>
	 * @return			{@link SubredditListing}
	 * @throws			RedditEngineException
	 */
	public SubredditListing searchSubreddits(String query, String before, String after, int count, int limit, String show) throws RedditEngineException{
		StringBuilder path = new StringBuilder();
		path.append("search");
		String which = path.toString();
		return getSubreddits(which, query, before, after, count, limit, show);
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
	public SubredditListing getSubreddits(String which, String before, String after, int count, int limit, String show) throws RedditEngineException {
		return getSubreddits(which, null, before, after, count, limit, show);
	}

	/**
	 * Implementation for many subreddit api methods
	 */
	private SubredditListing getSubreddits(String which, String query, String before, String after, int count, int limit, String show) throws RedditEngineException{
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
		if(query != null) {
			params.add(new BasicNameValuePair("q", query));
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
			Log.w(getClass().getName(), e.getMessage());
			ret = new SubredditListing();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return ret;
	}
}
