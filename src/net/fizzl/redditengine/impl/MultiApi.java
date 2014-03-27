package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;

import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.Multi;

public class MultiApi extends BaseApi {
	public Multi[] getMyMultis() throws RedditEngineException{
		String url = String.format("%s%s", UrlUtils.BASE_URL, "/api/multi/mine");
		Multi[] retval = null;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream in = client.get(url,null);
			retval = GsonTemplate.fromInputStream(in, Multi[].class);
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

	public void deleteMulti(String path){
		throw new UnimplementedException();
	}

	public Multi getMulti(String path){
		throw new UnimplementedException();
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
