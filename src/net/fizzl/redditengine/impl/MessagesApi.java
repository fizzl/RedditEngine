package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.MessageListing;

public class MessagesApi extends BaseApi {
	public void block(String thingId){
		throw new UnimplementedException();
	}

	public void compose(String to, String subject, String text, String captcha, String captchaIdentity){
		throw new UnimplementedException();
	}

	/**
	 * Read message
	 * 
	 * @param thingId	fullname of a thing
	 * @throws RedditEngineException 
	 */
	public void readMessage(String thingId) throws RedditEngineException {
		// POST /api/read_message
		String url = String.format("%s/api/read_message", UrlUtils.BASE_URL);
		this.readOrUnreadMessage(url, thingId);
	}
	
	/**
	 * Unread message
	 * 
	 * @param thingId	fullname of a thing
	 * @throws RedditEngineException
	 */
	public void unreadMessage(String thingId) throws RedditEngineException{
		// POST /api/unread_message
		String url = String.format("%s/api/unread_message", UrlUtils.BASE_URL);
		this.readOrUnreadMessage(url, thingId);		
	}
	
	/**
	 * Helper function for {@link #readMessage(String)}, {@link #unreadMessage(String)}
	 * 
	 * @param url		REST API url
	 * @param thingId	fullname of message
	 * @throws RedditEngineException
	 */
	private void readOrUnreadMessage(String url, String thingId) throws RedditEngineException {	
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", thingId));
		
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.post(url, params);
			Object response = GsonTemplate.fromInputStream(is, Object.class);  // {}
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}	
	}

	public MessageListing getMessageListing(String where, boolean mark, String mid, String before, 
			String after, int count, int limit, String show) throws RedditEngineException{
		String url = String.format("%s/message/%s.json", UrlUtils.BASE_URL, where);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("mark", String.valueOf(mark)));
		params.add(new BasicNameValuePair("count", String.valueOf(count)));
		params.add(new BasicNameValuePair("limit", String.valueOf(limit)));
		if (mid != null) {
			params.add(new BasicNameValuePair("mid", mid));
		}
		if (before != null) {
			params.add(new BasicNameValuePair("before", before));
		}
		if (after != null) {
			params.add(new BasicNameValuePair("after", after));
		}
		if (show != null) {
			params.add(new BasicNameValuePair("show", show));
		}
		
		MessageListing retval = null;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(url, params);
			retval = MessageListing.fromInputStream(is);
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

}
