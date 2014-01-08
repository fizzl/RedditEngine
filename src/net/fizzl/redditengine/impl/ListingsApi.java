package net.fizzl.redditengine.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.fizzl.redditengine.data.CommentListing;
import net.fizzl.redditengine.data.LinkListing;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class ListingsApi extends BaseApi {
	public LinkListing getLinkListingByName(String name) {
		throw new UnimplementedException();
	}

	public LinkListing getLinkListing(String subreddit, String order,
			String topScope, String before, String after, int limit)
			throws RedditEngineException {
		StringBuilder path = new StringBuilder();
		path.append("/r/");
		path.append(subreddit);

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
