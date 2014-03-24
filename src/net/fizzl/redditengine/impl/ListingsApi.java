package net.fizzl.redditengine.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.fizzl.redditengine.data.CommentListing;
import net.fizzl.redditengine.data.LinkListing;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * This class implements listings portion of the RedditApi
 * 
 * @see net.fizzl.redditengine.RedditApi
 * @see LinkListing
 */
public class ListingsApi extends BaseApi {
	/**
	 * Get a listing of links by fullname
	 * 
	 * @param names	list of fullnames for links separated by commas or spaces
	 * @return		{@link LinkListing}
	 * @throws RedditEngineException 
	 */
	public LinkListing getLinkListingByName(String names) throws RedditEngineException {
		StringBuilder path = new StringBuilder();
		path.append("/by_id/");
		path.append(names);  // TODO check if this is well-formed?
		String url = UrlUtils.getGetUrl(path.toString());
		SimpleHttpClient client = SimpleHttpClient.getInstance();
		LinkListing response = new LinkListing();
		try {
			InputStream is = client.get(url, null);
			response = LinkListing.fromInputStream(is);
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return response;
	}

	/**
	 * Get a Link Listing
	 * 
	 * <p/><tt>before</tt> and <tt>after</tt> are mutually exclusive.
	 * 
	 * @param subreddit		(optional) Get link listing from this subreddit.
	 * @param order			one of (<tt>hot</tt>, <tt>new</tt>, <tt>random</tt>, <tt>top</tt>, <tt>controversial</tt>)
	 * @param topScope
	 * @param before		fullname of a thing in the listing to use as the anchor point of the slice
	 * @param after			fullname of a thing in the listing to use as the anchor point of the slice
	 * @param limit			the maximum number of items desired (default: 25, maximum: 100)
	 * @return {@link LinkListing}
	 * @throws RedditEngineException
	 */
	public LinkListing getLinkListing(String subreddit, String order,
			String topScope, String before, String after, int limit)
			throws RedditEngineException {
		StringBuilder path = new StringBuilder();
		if(subreddit != null) {
			path.append("/r/");
			path.append(subreddit);
		}

		// hot/new/etc...
		if (order != null) {
			path.append("/");
			path.append(order);
		}

		String url = UrlUtils.getGetUrl(path.toString());

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (after != null) {
			params.add(new BasicNameValuePair("after", after));
		} else if (before != null) {
			params.add(new BasicNameValuePair("before", before));
		}

		if (limit > 0) {
			params.add(new BasicNameValuePair("limit", Integer.toString(limit)));
		}

		SimpleHttpClient client = SimpleHttpClient.getInstance();
		LinkListing ret = null;
		try {
			InputStream is = client.get(url, params);
			ret = LinkListing.fromInputStream(is);
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return ret;
	}

	/**
	 * Get a Comment Listing
	 * 
	 * @param subreddit
	 * @param article		ID36 of a link
	 * @param comment		(optional) ID36 of a comment
	 * @param context		an integer between 0 and 8
	 * @param depth			(optional) an integer
	 * @param limit			(optional) an integer
	 * @param sort			one of (<tt>confidence, top, new, hot, controversial, old, random</tt>)
	 * @return	{@link CommentListing}
	 * @throws RedditEngineException
	 */
	public CommentListing getCommentListing(String subreddit, String article,
			String comment, int context, int depth, int limit, String sort)
			throws RedditEngineException {
		StringBuilder path = new StringBuilder();
		path.append("/r/");
		path.append(subreddit);
		path.append("/comments/");
		path.append(article);
		String url = UrlUtils.getGetUrl(path.toString());

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (comment != null) {
			params.add(new BasicNameValuePair("comment", comment));
		}
		if (context > 0) {
			params.add(new BasicNameValuePair("context", Integer
					.toString(context)));
		}
		if (depth > 0) {
			params.add(new BasicNameValuePair("depth", Integer.toString(depth)));
		}
		if (limit > 0) {
			params.add(new BasicNameValuePair("limit", Integer.toString(limit)));
		}
		if (sort != null) {
			params.add(new BasicNameValuePair("sort", sort));
		}

		SimpleHttpClient client = SimpleHttpClient.getInstance();
		CommentListing ret = null;
		try {
			InputStream is = client.get(url, params);
			ret = CommentListing.fromInputStream(is);
			is.close();
		} catch(Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return ret;
	}
}
