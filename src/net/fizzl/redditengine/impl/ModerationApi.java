package net.fizzl.redditengine.impl;

import net.fizzl.redditengine.data.ModlogListing;

public class ModerationApi extends BaseApi {
	public ModlogListing getModlogListing(String subreddit, String type, String mod, String before, String after, int count, int limit){
		throw new UnimplementedException();
	}

	public void acceptModeratorInvite(String subreddit){
		throw new UnimplementedException();
	}

	public void approve(String thingId){
		throw new UnimplementedException();
	}

	public void distinguish(String thingId, String how){
		throw new UnimplementedException();
	}

	public void ignoreReports(String thingId){
		throw new UnimplementedException();
	}

	public void unignoreReports(String thingId){
		throw new UnimplementedException();
	}

	public void leaveContributor(String thingId){
		throw new UnimplementedException();
	}

	public void leaveModerator(String thingId){
		throw new UnimplementedException();
	}

	public void remove(String thing, boolean spam){
		throw new UnimplementedException();
	}

	public void getStyleSheet(String subreddit){
		throw new UnimplementedException();
	}

}
