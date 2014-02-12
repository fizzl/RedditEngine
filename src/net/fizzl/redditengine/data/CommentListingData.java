package net.fizzl.redditengine.data;

public class CommentListingData extends ListingData {
	Comment[] children;

	public Comment[] getChildren() {
		return children;
	}

	public void setChildren(Comment[] children) {
		this.children = children;
	}
	
}
