package net.fizzl.redditengine.data;

/**
 * Listing that contains Messages
 */
public class MessageListingData extends ListingData {
	Message[] children;

	public Message[] getChildren() {
		return children;
	}

	public void setChildren(Message[] children) {
		this.children = children;
	}
}
