package net.fizzl.redditengine.data;

/**
 * A Listing that contains Comments as Listing Things
 * @see Comment
 */
public class CommentListingData extends ListingData {
	Comment[] children;

	public Comment[] getChildren() {
		return children;
	}

	public void setChildren(Comment[] children) {
		this.children = children;
	}
	
}
