package net.fizzl.redditengine.impl;

import net.fizzl.redditengine.data.Listing;
import net.fizzl.redditengine.data.User;

public class UserApi extends BaseApi {
	public void friend(String user, String container, String type, String permissions, String note){
		throw new UnimplementedException();
	}

	public void unfriend(String user, String thingId, String container, String type){
		throw new UnimplementedException();
	}

	public void setPermissions(String user, String subreddit, String permissions){
		throw new UnimplementedException();
	}
	public boolean isUsernameAvailable(String user){
		throw new UnimplementedException();
	}

	public User aboutUser(String user){
		throw new UnimplementedException();
	}

	public Listing<?> getUserListing(String user, String what){
		throw new UnimplementedException();
	}

}
