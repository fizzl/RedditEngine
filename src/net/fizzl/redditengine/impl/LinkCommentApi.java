package net.fizzl.redditengine.impl;

import net.fizzl.redditengine.data.CommentListing;

public class LinkCommentApi extends BaseApi {
	public void comment(String parentId, String text){
		throw new UnimplementedException();
	}

	public void delete(String thingId){
		throw new UnimplementedException();
	}

	public void edit(String thingId, String text){
		throw new UnimplementedException();
	}

	public void hide(String thingId){
		throw new UnimplementedException();
	}

	public void unhide(String thingId){
		throw new UnimplementedException();
	}

	public void info(String url, String thingId, int limit){
		throw new UnimplementedException();
	}

	public void markNSFW(String thingId){
		throw new UnimplementedException();
	}

	public void unmarkNSFW(String thingId){
		throw new UnimplementedException();
	}

	public CommentListing moreChildren(String linkId, String children, String moreId, String sort){
		throw new UnimplementedException();
	}

	public void report(String thingId){
		throw new UnimplementedException();
	}

	public void save(String thingId){
		throw new UnimplementedException();
	}

	public void unsave(String thingId){
		throw new UnimplementedException();
	}

	public void setContestMode(String thingId, boolean state){
		throw new UnimplementedException();
	}

	public void setSticky(String thingId, boolean state){
		throw new UnimplementedException();
	}

	public void submit(String subreddit, String title, String kind, String url, String text, 
			String captcha, String captchaIden, boolean resubmit, boolean save, boolean sendReplies){
		throw new UnimplementedException();
	}

	public void vote(String thingId, int dir){
		throw new UnimplementedException();
	}

}
