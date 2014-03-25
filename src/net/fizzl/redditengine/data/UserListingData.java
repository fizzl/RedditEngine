package net.fizzl.redditengine.data;

import net.fizzl.redditengine.data.type.UserListingItem;

/**
 * A Listing that contains Comments or Links as Listing Things
 */
public class UserListingData extends ListingData {
	UserListingItem[] children;	
	
	public UserListingItem[] getChildren() {
		return children;
	}

	public void setChildren(UserListingItem[] children) {
		this.children = children;
	}
}
