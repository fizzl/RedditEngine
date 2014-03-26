package net.fizzl.redditengine.impl;

import java.io.InputStream;

import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.ModlogListing;
import net.fizzl.redditengine.data.StyleSheet;

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

	/**
	 * Returns reddits stylesheet
	 * @throws RedditEngineException
	 */
	public StyleSheet getStyleSheet(String subreddit) throws RedditEngineException{
		String path = String.format("/r/%s/about/stylesheet", subreddit);
		String url = UrlUtils.getGetUrl(path);
		
		StyleSheet response = new StyleSheet();
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(url, null);
			response = GsonTemplate.fromInputStream(is, StyleSheet.class);
			is.close();
		} catch (Exception e) {
			RedditEngineException re = new RedditEngineException(e);
			throw re;
		}
		return response;
	}

}
