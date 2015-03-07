package net.fizzl.redditengine.impl;

import net.fizzl.redditengine.data.FlairListing;

public class FlairApi extends BaseApi {
	public void clearFlairTemplates(String subreddit, String flair_type){
		throw new UnimplementedException();
	}

	public void deleteFlair(String subreddit, String name){
		throw new UnimplementedException();
	}

	public void deleteFlairTemplate(String subreddit, String templateId){
		throw new UnimplementedException();
	}

	public void flair(String subreddit, String name, String link, String text, String cssClass){
		throw new UnimplementedException();
	}

	public void flairConfig(String subreddit, boolean enabled, String flairPosition, 
			boolean flairSelfAssignEnabled, String linkFlairPosition, boolean linkFlairSelfAssignEnabled){
		throw new UnimplementedException();
	}

	public void flairCsv(String subreddit, String flairCsv){
		throw new UnimplementedException();
	}

	public FlairListing getFlairListing(String subreddit, String before, String after, int count, int limit, String name){
		throw new UnimplementedException();
	}

	public void flairTemplate(String subreddit, String cssClass, String flairTemplateId, String flairType, String text, boolean textEditable){
		throw new UnimplementedException();
	}

	public void selectFlair(String subreddit, String flairTemplateId, String link, String name, String text){
		throw new UnimplementedException();
	}

	public void setFlairEnabled(String subreddit, boolean enabled){
		throw new UnimplementedException();
	}
}
