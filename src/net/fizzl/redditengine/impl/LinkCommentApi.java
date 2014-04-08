package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import net.fizzl.redditengine.data.CommentListing;
import net.fizzl.redditengine.data.CommentResponse;
import net.fizzl.redditengine.data.GsonTemplate;

public class LinkCommentApi extends BaseApi {
	public CommentResponse comment(String parentId, String text) throws RedditEngineException{
		String url = String.format("%s/api/comment", UrlUtils.BASE_URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (text != null) {
			params.add(new BasicNameValuePair("text", text));
		}
		if (parentId != null) {
			params.add(new BasicNameValuePair("thing_id", parentId));
		}
		params.add(new BasicNameValuePair("api_type", "json"));
		CommentResponse retval = null;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			retval = GsonTemplate.fromInputStream(is, CommentResponse.class);
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
