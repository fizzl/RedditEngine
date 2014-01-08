package net.fizzl.redditengine.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.fizzl.redditengine.data.Subreddit;
import net.fizzl.redditengine.data.SubredditListing;
import net.fizzl.redditengine.data.SubredditSettings;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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

	public void subscribeSubreddit(String subreddit){
		throw new UnimplementedException();
	}

	public void unsubscribeSubreddit(String subreddit){
		throw new UnimplementedException();
	}

	public Subreddit aboutSubreddit(String subreddit){
		throw new UnimplementedException();
	}

	public SubredditSettings getSubredditSettings(String subreddit){
		throw new UnimplementedException();
	}

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
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return ret;
	}

	public SubredditListing searchSubreddits(String query, String before, String after, int count, int limit, String show){
		throw new UnimplementedException();
	}

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
