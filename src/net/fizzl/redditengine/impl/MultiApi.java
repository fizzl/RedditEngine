package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;

import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.MultiData;

public class MultiApi extends BaseApi {
	public MultiData[] getMyMultis() throws RedditEngineException{
		String url = String.format("%s%s", UrlUtils.BASE_URL, "/api/multi/mine");
		MultiData[] retval = null;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream in = client.get(url, null);
			// Reponse may be an array of MultiDatas or just one in case of an error (403)
			// example: {"explanation": "please login to do that", "reason": "USER_REQUIRED"}
			// any error status will throw an exception
			Object response = GsonTemplate.fromInputStream(in, Object.class);
			in.close();
			Gson gson = new Gson();
			String json = gson.toJson(response);
			if (response instanceof List) {
				// reinterpret as MultiData[]
				retval = gson.fromJson(json, MultiData[].class);
			} else if (response instanceof Map) {
				// reinterpret as MultiData
				MultiData multiData = gson.fromJson(json, MultiData.class);
				retval = new MultiData[] { multiData };
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

	public MultiData getMulti(String path) throws RedditEngineException{
		// TODO find a valid multireddit path and try that
		String url = String.format("%s/api/multi/%s", UrlUtils.BASE_URL, path);
		MultiData retval;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			// may return HTTP status 404 and a JSON response
			// {"fields": ["multipath"], "explanation": "that multireddit doesn't exist", "reason": "MULTI_NOT_FOUND"}
			// any error status code will throw an exception
			InputStream in = client.get(url, null);
			retval = GsonTemplate.fromInputStream(in, MultiData.class);
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

	public String getMultiDescription(String path){
		throw new UnimplementedException();
	}

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
