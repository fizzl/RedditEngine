package net.fizzl.redditengine.data;

public class SubredditListingData extends ListingData {
	Subreddit[] children;

	public Subreddit[] getChildren() {
		return children;
	}

	public void setChildren(Subreddit[] children) {
		this.children = children;
	}
}
