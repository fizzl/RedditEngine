package net.fizzl.redditengine.data;

/**
 * A Listing that contains Links as Listing Things
 * @see Link
 */
public class LinkListingData extends ListingData {
	Link[] children;

	public Link[] getChildren() {
		return children;
	}

	public void setChildren(Link[] children) {
		this.children = children;
	}
	
}
