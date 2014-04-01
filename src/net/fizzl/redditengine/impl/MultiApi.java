package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

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

	/**
	 * Delete a multi.
	 * 
	 * @param path	multireddit url path
	 * @throws RedditEngineException
	 */
	public void deleteMulti(String path) throws RedditEngineException{
		String url = String.format("%s/api/multi/%s", UrlUtils.BASE_URL, path);
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream in = client.delete(url);
			// TODO error details in response, won't get to this because an exception is thrown
			// seems normally the response is null?
			MultiData response = GsonTemplate.fromInputStream(in, MultiData.class);
			in.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
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
	
	// inner class for sending JSON data
	private class Model {
		private Subreddit[] subreddits;
		private String visibility;
		class Subreddit {
			private String name;
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
		}
		public Subreddit[] getSubreddits() {
			return subreddits;
		}
		public void setSubreddits(Subreddit[] subreddits) {
			this.subreddits = subreddits;
		}
		public String getVisibility() {
			return this.visibility;
		}
		public void setVisibility(String visibility) {
			this.visibility =  visibility;
		}
	}
	
	private String booleanToVisibility (boolean visibility) {
		if (visibility == true) {
			return "public";
		} else { 
			return "private";
		}
	}
	
	private boolean visibilityToBoolean (String visibility) {
		if (visibility.equals("public")) {
			return true;
		} else if (visibility.equals("private")) {
			return false;
		} else {
			Log.e(getClass().getName(), visibility);
			return false;
		}
	}

	/**
	 * Create a multi. Throws 409 Conflict if the multi already exists.
	 * 	
	 * @param path			multireddit url path
	 * @param subreddits	subreddit names
	 * @param isPublic		{@code true} for 'public' and {@code false} for 'private'
	 * @throws RedditEngineException
	 */
	public void createOrEditMulti(String path, String[] subreddits, boolean isPublic) throws RedditEngineException{
		// TODO how to know when to edit? (PUT)
		Model model = new Model();
		List<Model.Subreddit> modelSubreddits = new ArrayList<Model.Subreddit>();
		for (String subreddit: subreddits) {
			Model.Subreddit sr = model.new Subreddit();
			sr.setName(subreddit);
			modelSubreddits.add(sr);
		}
		Model.Subreddit[] array = modelSubreddits.toArray(new Model.Subreddit[modelSubreddits.size()]);
		model.setSubreddits(array);
		model.setVisibility(booleanToVisibility(isPublic));
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String jsonModel = new Gson().toJson(model);
		params.add(new BasicNameValuePair("model", jsonModel));
		params.add(new BasicNameValuePair("multipath", path));
		
		String url = String.format("%s/api/multi/%s", UrlUtils.BASE_URL, path);

		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream in = client.post(url, params);
			LabeledMulti response = GsonTemplate.fromInputStream(in, LabeledMulti.class);
			// TODO anything to be done with the response?
			Log.d(getClass().getName(), new Gson().toJson(response));  // debug log for now
			in.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
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

	/**
	 * Change a multi's markdown description.
	 * 
	 * @param path	multireddit url path
	 * @param text	raw markdown text
	 * @throws RedditEngineException
	 */
	public void setMultiDescription(String path, String text) throws RedditEngineException{
		String url = String.format("%s/api/multi/%s/description", UrlUtils.BASE_URL, path);
		
		LabeledMultiDescriptionData data = new LabeledMultiDescriptionData();
		data.setBody_md(text);	
		// should ignore null variables so we get just {"body_md":text}
		String model = new Gson().toJson(data);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("model", model));
		params.add(new BasicNameValuePair("multipath", path));
		
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream in = client.put(url, params);
			LabeledMultiDescription response = GsonTemplate.fromInputStream(in, LabeledMultiDescription.class);
			if (response != null && response.getData() != null
				&& response.getData().getBody_md() != null 
				&& response.getData().body_md.equalsIgnoreCase(text)) {
				// response shows the description is ok
			} else {
				// TODO should we throw an exception or not?
				if (response != null) {
					String json = new Gson().toJson(response);
					Log.e(getClass().getName(), String.format(
						"did not find \'body_md==%s\' in the response (%s)", text, json));
				}
			}
			in.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}

	}

	/**
	 * Remove a subreddit from a multi.
	 * 
	 * @param path		multireddit url path
	 * @param subreddit	subreddit name
	 * @throws RedditEngineException
	 */
	public void removeSubredditFromMulti(String path, String subreddit) throws RedditEngineException{
		// DELETE /api/multi/multipath/r/srname
		String url = String.format("%s/api/multi/%s/r/%s", UrlUtils.BASE_URL, path, subreddit);
		
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			// request URI should completely identify the resource to delete
			InputStream in = client.delete(url);
			// response is normally null
			Object response = GsonTemplate.fromInputStream(in, Object.class);			
			in.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
	}

	/**
	 * Add a subreddit to a multi.
	 * 
	 * @param path		multireddit url path
	 * @param subreddit	subreddit name
	 * @throws RedditEngineException
	 */
	public void addSubredditToMulti(String path, String subreddit) throws RedditEngineException{
		// PUT /api/multi/multipath/r/srname
		String url = String.format("%s/api/multi/%s/r/%s", UrlUtils.BASE_URL, path, subreddit);
		
		Map<String, String> map = new HashMap<String, String>(); // "anonymous class" for GSON
		map.put("name", subreddit);
		String model = new Gson().toJson(map);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("model", model));
		params.add(new BasicNameValuePair("multipath", path));
		params.add(new BasicNameValuePair("srname", subreddit));
		
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream in = client.put(url, params);
			// response is {name=subreddit}
			Object response = GsonTemplate.fromInputStream(in, Object.class);
			if ((response != null) && (response instanceof Map)) {
				Map<String, String> responseMap = (Map<String, String>) response;
				String value = responseMap.get("name");
				if (value != null && value.equalsIgnoreCase(subreddit)) {
					// return value is ok
				}
			}
			in.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
				
	}

	public String getSubredditInfoInMulti(String path, String subreddit) throws RedditEngineException{
		// GET /api/multi/multipath/r/srname
		String url = String.format("%s/api/multi/%s/r/%s", UrlUtils.BASE_URL, path, subreddit);

		// TODO are these really needed
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("multipath", path));
		params.add(new BasicNameValuePair("srname", subreddit));
		
		String retval = null;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream in = client.get(url, params);
			Object response = GsonTemplate.fromInputStream(in, Object.class);
			// TODO what should the return value be?
			if (response != null) {
				retval = new Gson().toJson(response);
			}
			in.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
		
		return retval;
	}

	public void renameMulti(String path, String from, String to){
		throw new UnimplementedException();
	}

}
