package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import net.fizzl.redditengine.data.CommentListing;
import net.fizzl.redditengine.data.CommentResponse;
import net.fizzl.redditengine.data.EditResponse;
import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.SubmitResponse;

public class LinkCommentApi extends BaseApi {
	/**
	 * Submit a new comment or reply to a message.
	 * 
	 * @param parentId	parent is the fullname of the thing being replied to
	 * @param text		raw markdown text
	 * @return			{@link CommentResponse}
	 * @throws RedditEngineException
	 */
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

	/**
	 * Delete a Link or Comment.
	 * 
	 * @param thingId	fullname of a thing created by the user
	 * @throws RedditEngineException
	 */
	public void delete(String thingId) throws RedditEngineException{
		String url = String.format("%s/api/del", UrlUtils.BASE_URL);
		postUrlWithId(url, thingId);
	}

	/**
	 * Edit the body text of a comment or self-post.
	 * 
	 * @param thingId	fullname of a thing created by the user
	 * @param text		raw markdown text
	 * @throws RedditEngineException 
	 */
	public EditResponse edit(String thingId, String text) throws RedditEngineException{
		// possible answers:
		// {json={errors=[[NOT_AUTHOR, you can't do that, thing_id]]}}
		String url = String.format("%s/api/editusertext", UrlUtils.BASE_URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (text != null) {
			params.add(new BasicNameValuePair("text", text));
		}
		if (thingId != null) {
			params.add(new BasicNameValuePair("thing_id", thingId));
		}
		params.add(new BasicNameValuePair("api_type", "json"));

		EditResponse retval = null;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			retval = GsonTemplate.fromInputStream(is, EditResponse.class);
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
		postUrlWithId(url, thingId);
	}

	/**
	 * Unhide a link.
	 * 
	 * @param thingId	fullname of a thing
	 * @throws RedditEngineException
	 */
	public void unhide(String thingId) throws RedditEngineException{
		String url = String.format("%s/api/unhide", UrlUtils.BASE_URL);
		postUrlWithId(url, thingId);
	}

	/**
	 * Get a link by fullname or a list of links by URL.</p>
	 * If both url and id are provided, id will take precedence.
	 * 
	 * @param url		a valid URL
	 * @param thingId	fullname of a thing
	 * @param limit		the maximum number of items desired (default: 25, maximum: 100)
	 * @throws RedditEngineException 
	 */
	public void info(String url, String thingId, int limit) throws RedditEngineException{
		// GET [/r/subreddit]/api/info
		StringBuilder sb = new StringBuilder();
		sb.append(UrlUtils.BASE_URL);		
		// TODO subreddit parameter: if (subreddit != null) { sb.append("/r/" + subreddit); }
		sb.append("/api/info");		
		String path = sb.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (thingId != null) {
			params.add(new BasicNameValuePair("id", thingId));
		}
		if (url != null) {
			params.add(new BasicNameValuePair("url", url));
		}
		params.add(new BasicNameValuePair("limit", String.valueOf(limit)));
		
		try {
			// TODO response is HTML, what should params be?
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(path, params);
			java.io.StringWriter sr = new java.io.StringWriter();
			IOUtils.copy(is, sr, "UTF-8");
			String response = sr.toString();
			//Object retval = GsonTemplate.fromInputStream(is, Object.class);
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
	 * Mark a link NSFW.
	 * 
	 * @param thingId	fullname of a thing
	 * @throws RedditEngineException 
	 */
	public void markNSFW(String thingId) throws RedditEngineException{
		// TODO returns 403, how to test?
		String url = String.format("%s/api/marknsfw", UrlUtils.BASE_URL);
		postUrlWithId(url, thingId);
	}
	
	/**
	 * Call POST method with no specific response JSON type
	 * 
	 * @param url		API url
	 * @param thingId	fullname of a thing
	 * @throws RedditEngineException
	 */
	private void postUrlWithId(String url, String thingId) throws RedditEngineException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (thingId != null) {
			params.add(new BasicNameValuePair("id", thingId));
		}
		params.add(new BasicNameValuePair("api_type", "json"));
		postUrlWithParams(url, params);
	}
	
	/**
	 * Call POST method with no specific response JSON type
	 * 
	 * @param url		API url
	 * @param params	list of POST parameters
	 * @throws RedditEngineException
	 */
	private void postUrlWithParams(String url, List<NameValuePair> params) throws RedditEngineException {
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
	 * Remove the NSFW marking from a link.
	 * 
	 * @param thingId	fullname of a thing
	 * @throws RedditEngineException 
	 */
	public void unmarkNSFW(String thingId) throws RedditEngineException{
		// TODO returns 403, how to test?
		String url = String.format("%s/api/unmarknsfw", UrlUtils.BASE_URL);
		postUrlWithId(url, thingId);
	}

	public CommentListing moreChildren(String linkId, String children, String moreId, String sort){
		throw new UnimplementedException();
	}

	/**
	 * 
	 * @param thingId		fullname of a thing
	 * @throws RedditEngineException 
	 */
	public void report(String thingId) throws RedditEngineException{
		String url = String.format("%s/api/report", UrlUtils.BASE_URL);
		postUrlWithId(url, thingId);
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

		postUrlWithParams(url, params);
	}

	/**
	 * Unsave a link or comment.
	 * 
	 * @param thingId	fullname of a thing
	 * @throws RedditEngineException
	 */
	public void unsave(String thingId) throws RedditEngineException{
		String url = String.format("%s/api/unsave", UrlUtils.BASE_URL);
		postUrlWithId(url, thingId);
	}

	/**
	 * Set or unset "contest mode" for a link's comments.
	 * 
	 * @param id		fullname of a thing
	 * @param state		boolean value
	 * @throws RedditEngineException 
	 */
	public void setContestMode(String thingId, boolean state) throws RedditEngineException{
		// TODO returns 403
		String url = String.format("%s/api/set_contest_mode", UrlUtils.BASE_URL);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_type", "json"));
		params.add(new BasicNameValuePair("id", thingId));
		params.add(new BasicNameValuePair("state", String.valueOf(state)));
		
		postUrlWithParams(url, params);
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
		
		postUrlWithParams(url, params);
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
		
		postUrlWithParams(url, params);
	}

}
