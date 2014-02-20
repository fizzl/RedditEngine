package net.fizzl.redditengine.data;

/**
 * A Listing that contains Subreddits as its Listing Things
 */
public class SubredditListingData extends ListingData {
	Subreddit[] children;

	public Subreddit[] getChildren() {
		return children;
	}

	public void setChildren(Subreddit[] children) {
		this.children = children;
	}
}
