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
import net.fizzl.redditengine.data.SubmitResponse;

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

	public void delete(String thingId) throws RedditEngineException{
		// POST /api/del
		String url = String.format("%s/api/del", UrlUtils.BASE_URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (thingId != null) {
			params.add(new BasicNameValuePair("id", thingId));
		}
		params.add(new BasicNameValuePair("api_type", "json"));  // needed?
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			Object retval = GsonTemplate.fromInputStream(is, Object.class);  // response is {}
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
	}

	/**
	 * Edit the body text of a comment or self-post.
	 * 
	 * @param thingId	fullname of a thing created by the user
	 * @param text		raw markdown text
	 * @throws RedditEngineException 
	 */
	public CommentResponse edit(String thingId, String text) throws RedditEngineException{
		// TODO more specific return type
		String url = String.format("%s/api/editusertext", UrlUtils.BASE_URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (text != null) {
			params.add(new BasicNameValuePair("text", text));
		}
		if (thingId != null) {
			params.add(new BasicNameValuePair("thing_id", thingId));
		}
		params.add(new BasicNameValuePair("api_type", "json"));

		// possible answers
		// {json={errors=[[NOT_AUTHOR, you can't do that, thing_id]]}}
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

	/**
	 * This removes a link from the user's default view of subreddit listings.
	 * 
	 * @param thingId	fullname of a link
	 * @throws RedditEngineException
	 */
	public void hide(String thingId) throws RedditEngineException{
		String url = String.format("%s/api/hide", UrlUtils.BASE_URL);
		hideOrUnhide(url, thingId);
	}
	
	private void hideOrUnhide(String url, String thingId) throws RedditEngineException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (thingId != null) {
			params.add(new BasicNameValuePair("id", thingId));
		}
		params.add(new BasicNameValuePair("api_type", "json"));  // needed?
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			Object retval = GsonTemplate.fromInputStream(is, Object.class);  // response is {}
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}		
	}

	/**
	 * Unhide a link.
	 * 
	 * @param thingId	fullname of a thing
	 * @throws RedditEngineException
	 */
	public void unhide(String thingId) throws RedditEngineException{
		String url = String.format("%s/api/unhide", UrlUtils.BASE_URL);
		hideOrUnhide(url, thingId);
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

	/**
	 * Save a link or comment.
	 * 
	 * @param category	a category name
	 * @param thingId	fullname of a thing
	 * @throws RedditEngineException 
	 */
	public void save(String thingId, String category) throws RedditEngineException{
		String url = String.format("%s/api/save", UrlUtils.BASE_URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (thingId != null) {
			params.add(new BasicNameValuePair("id", thingId));
		}
		if (category != null) {
			params.add(new BasicNameValuePair("category", category));
		}
		params.add(new BasicNameValuePair("api_type", "json"));

		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			Object retval = GsonTemplate.fromInputStream(is, Object.class);  // response is {}
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
	}

	/**
	 * Unsave a link or comment.
	 * 
	 * @param thingId	fullname of a thing
	 * @throws RedditEngineException
	 */
	public void unsave(String thingId) throws RedditEngineException{
		String url = String.format("%s/api/unsave", UrlUtils.BASE_URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (thingId != null) {
			params.add(new BasicNameValuePair("id", thingId));
		}
		params.add(new BasicNameValuePair("api_type", "json"));

		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			Object retval = GsonTemplate.fromInputStream(is, Object.class);  // response is {}
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
	}

	public void setContestMode(String thingId, boolean state){
		throw new UnimplementedException();
	}

	/**
	 * Set or unset a self-post as the sticky post in its subreddit.
	 * 
	 * @param thingId	fullname of a thing
	 * @param state		state is a boolean that indicates whether to sticky or unsticky this post - true to sticky, false to unsticky.
	 * @throws RedditEngineException
	 */
	public void setSticky(String thingId, boolean state) throws RedditEngineException{
		// TODO result is 403, how to set sticky?
		String url = String.format("%s/api/set_subreddit_sticky", UrlUtils.BASE_URL);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_type", "json"));
		params.add(new BasicNameValuePair("id", thingId));
		params.add(new BasicNameValuePair("state", String.valueOf(state)));
		
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			Object retval = GsonTemplate.fromInputStream(is, Object.class);
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
	}

	/**
	 * Submit a link to a subreddit.
	 * 
	 * @param subreddit		name of a subreddit
	 * @param title			title of the submission. up to 300 characters long
	 * @param kind			one of (link, self)
	 * @param url			a valid URL
	 * @param text			raw markdown text
	 * @param captcha		the user's response to the CAPTCHA challenge
	 * @param captchaIden	the identifier of the CAPTCHA challenge
	 * @param resubmit		boolean value
	 * @param save			boolean value
	 * @param sendReplies	boolean value
	 * @return				{@link SubmitResponse}
	 * @throws RedditEngineException
	 */
	public SubmitResponse submit(String subreddit, String title, String kind, String url, String text, 
			String captcha, String captchaIden, boolean resubmit, boolean save, boolean sendReplies) throws RedditEngineException{
		String path = String.format("%s/api/submit", UrlUtils.BASE_URL);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sr", subreddit));
		params.add(new BasicNameValuePair("api_type", "json"));
		params.add(new BasicNameValuePair("captcha", captcha));
		params.add(new BasicNameValuePair("iden", captchaIden));
		params.add(new BasicNameValuePair("kind", kind));
		params.add(new BasicNameValuePair("resubmit", String.valueOf(resubmit)));
		params.add(new BasicNameValuePair("save", String.valueOf(save)));
		params.add(new BasicNameValuePair("sendreplies", String.valueOf(sendReplies)));
		params.add(new BasicNameValuePair("text", text));
		params.add(new BasicNameValuePair("title", title));
		params.add(new BasicNameValuePair("extension", "json"));
		params.add(new BasicNameValuePair("then", "tb")); // then	one of (tb, comments)
		params.add(new BasicNameValuePair("url", url));
		
		// response can be:
		// {"json": {"errors": [["QUOTA_FILLED", "You've submitted too many links recently. Please try again in an hour.", null]]}}
		// {"json": {"errors": [], "data": {"url": "<URL>", "id": "<ID>", "name": "<FULLNAME>"}}}
		
		SubmitResponse retval = null;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(path, params);
			retval = GsonTemplate.fromInputStream(is, SubmitResponse.class);
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

	/**
	 * Cast a vote on a thing.
	 * 
	 * @param thingId	the fullname of the Link or Comment to vote on.
	 * @param dir		direction of the vote. Voting 1 is an upvote, -1 is a downvote, and 0 is equivalent to "un-voting" by clicking again on a highlighted arrow
	 * @throws RedditEngineException 
	 */
	public void vote(String thingId, int dir) throws RedditEngineException{
		String url = String.format("%s/api/vote", UrlUtils.BASE_URL);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("dir", String.valueOf(dir)));
		params.add(new BasicNameValuePair("id", thingId));
		
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			Object retval = GsonTemplate.fromInputStream(is, Object.class);
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
	}

}
