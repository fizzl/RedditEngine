package net.fizzl.redditengine.impl;

import net.fizzl.redditengine.data.LinkListing;

public class SearchApi extends BaseApi {
	public LinkListing search(String subreddit, String query, boolean restrictToSubreddit, String before, String after, int count, 
			int limit, String sort, String timeScope, String show) {
		throw new UnimplementedException();
	}
}
