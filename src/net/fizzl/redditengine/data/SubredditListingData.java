package net.fizzl.redditengine.data;

/**
 * A listing that contains subreddits
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
