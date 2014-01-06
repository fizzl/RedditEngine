package net.fizzl.redditengine.impl;

import net.fizzl.redditengine.data.Subreddit;
import net.fizzl.redditengine.data.SubredditListing;
import net.fizzl.redditengine.data.SubredditSettings;

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

	public SubredditListing getMySubreddits(String where, String before, String after, int count, int limit, String show){
		throw new UnimplementedException();
	}

	public SubredditListing searchSubreddits(String query, String before, String after, int count, int limit, String show){
		throw new UnimplementedException();
	}

	public SubredditListing listSubreddits(String which, String before, String after, int count, int limit, String show){
		throw new UnimplementedException();
	}
}
