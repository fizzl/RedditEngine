package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.ModlogListing;
import net.fizzl.redditengine.data.StyleSheet;

public class ModerationApi extends BaseApi {
	/**
	 * Get a list of recent moderation actions.
	 * 
	 * @param subreddit		subreddit
	 * @param type			one of (<tt>banuser, unbanuser, removelink, approvelink, removecomment, approvecomment, addmoderator, invitemoderator, uninvitemoderator, acceptmoderatorinvite, removemoderator, addcontributor, removecontributor, editsettings, editflair, distinguish, marknsfw, wikibanned, wikicontributor, wikiunbanned, wikipagelisted, removewikicontributor, wikirevise, wikipermlevel, ignorereports, unignorereports, setpermissions, sticky, unsticky</tt>)
	 * @param mod			(optional) a moderator filter
	 * @param before		fullname of a thing
	 * @param after			fullname of a thing
	 * @param count			a positive integer (default: 0)
	 * @param limit			the maximum number of items desired (default: 25, maximum: 500)
	 * @return				{@link ModlogListing}
	 * @throws RedditEngineException 
	 */
	public ModlogListing getModlogListing(String subreddit, String type, String mod, String before, String after, int count, int limit) throws RedditEngineException{
		// GET [/r/subreddit]/about/log[.json
		// TODO become moderator (or admin) to test this
		String url = String.format("%s/r/%s/about/log.json", UrlUtils.BASE_URL, subreddit);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (after != null) {
			params.add(new BasicNameValuePair("after", after));
		}
		if (before != null) {
			params.add(new BasicNameValuePair("before", before));
		}
		params.add(new BasicNameValuePair("count", String.valueOf(count)));
		params.add(new BasicNameValuePair("limit", String.valueOf(limit)));
		if (mod != null) {
			params.add(new BasicNameValuePair("mod", mod));
		}
		params.add(new BasicNameValuePair("show", "all"));
		if (type != null) {
			params.add(new BasicNameValuePair("type", type));
		}
		
		ModlogListing retval = null;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(url, params);
			retval = GsonTemplate.fromInputStream(is, ModlogListing.class);
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
		
		return retval;
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
