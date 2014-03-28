package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;

import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.LabeledMulti;
import net.fizzl.redditengine.data.MultiData;
import net.fizzl.redditengine.data.Thing;

public class MultiApi extends BaseApi {
	
	/**
	 * Return a list of multis belonging to the current user
	 * 
	 * @return {@link MultiData}[]
	 * @throws RedditEngineException
	 */
	public LabeledMulti[] getMyMultis() throws RedditEngineException{
		String url = String.format("%s%s", UrlUtils.BASE_URL, "/api/multi/mine");
		LabeledMulti[] retval = null;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			// Reponse may be an array of LabeledMultis or one MultiData in case of an error (403)
			// MultiData example: {"explanation": "please login to do that", "reason": "USER_REQUIRED"}			
			InputStream in = client.get(url, null);  // any error status will throw an exception
			Object response = GsonTemplate.fromInputStream(in, Object.class);
			in.close();
			Gson gson = new Gson();
			String json = gson.toJson(response);
			if (response instanceof List) {
				// reinterpret as LabeledMulti[]
				retval = gson.fromJson(json, LabeledMulti[].class);
			} else if (response instanceof Map) {
				// reinterpret as MultiData
				MultiData multiData = gson.fromJson(json, MultiData.class);
				// TODO how to return this?
			} else {
				throw new ClassCastException("don't know how to interpret response " + json);
			}
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			// TODO got a 403 but because of exception we don't know the exact error message
			throw new RedditEngineException(e);
		}
		
		return retval;
	}

	public void deleteMulti(String path){
		throw new UnimplementedException();
	}

	/**
	 * Return a multi's data and subreddit list by name
	 * 
	 * @param path	multireddit url path
	 * @return		{@link MultiData}
	 * @throws RedditEngineException
	 */
	public LabeledMulti getMulti(String path) throws RedditEngineException{
		// TODO response type varies. LabeledMulti is returned when there are no errors. Otherwise returned type is MultiData. how to solve?
		String url = String.format("%s/api/multi/%s", UrlUtils.BASE_URL, path);
		LabeledMulti retval;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			// may return HTTP status 404 and a JSON response
			// {"fields": ["multipath"], "explanation": "that multireddit doesn't exist", "reason": "MULTI_NOT_FOUND"}
			// any error status code will throw an exception
			InputStream in = client.get(url, null);
			retval = GsonTemplate.fromInputStream(in, LabeledMulti.class);
			in.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			// TODO got a 404 but because of exception we don't know the exact error message
			throw new RedditEngineException(e);
		}
		return retval;
	}

	public void createOrEditMulti(String path, String[] subreddits, boolean isPublic){
		throw new UnimplementedException();
	}

	public void copyMulti(String path, String from, String to){
		throw new UnimplementedException();
	}

	/**
	 * Return a multi's description
	 * 
	 * @param path	multireddit url path
	 * @return		{@link String}
	 * @throws RedditEngineException
	 */
	public String getMultiDescription(String path) throws RedditEngineException{
		String url = String.format("%s/api/multi/%s/description", UrlUtils.BASE_URL, path);
		String retval;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			// may return HTTP status 404 and a JSON response
			// {"fields": ["multipath"], "explanation": "that multireddit doesn't exist", "reason": "MULTI_NOT_FOUND"}			
			InputStream in = client.get(url, null);  // any error status code will throw an exception
			LabeledMultiDescription response = GsonTemplate.fromInputStream(in, LabeledMultiDescription.class);
			in.close();
			retval = response.getData().getBody_md();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			// TODO got a 404 but because of exception we don't know the exact error message
			throw new RedditEngineException(e);
		}
		return retval;
	}
	
	// make this class public if the client needs it
	private class LabeledMultiDescriptionData {
		String body_html;
		String body_md;
		
		public String getBody_html() {
			return body_html;
		}
		public void setBody_html(String body_html) {
			this.body_html = body_html;
		}
		public String getBody_md() {
			return body_md;
		}
		public void setBody_md(String body_md) {
			this.body_md = body_md;
		}
	}
	
	// make this class public if the client needs it
	private class LabeledMultiDescription extends Thing<LabeledMultiDescriptionData> {}

	public void setMultiDescription(String path, String text){
		throw new UnimplementedException();
	}

	public void removeSubredditFromMulti(String path, String subreddit){
		throw new UnimplementedException();
	}

	public void addSubredditToMulti(String path, String subreddit){
		throw new UnimplementedException();
	}

	public String getSubredditInfoInMulti(String path, String subreddit){
		throw new UnimplementedException();
	}

	public void renameMulti(String path, String from, String to){
		throw new UnimplementedException();
	}

}
