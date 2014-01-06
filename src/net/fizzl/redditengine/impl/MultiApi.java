package net.fizzl.redditengine.impl;

import net.fizzl.redditengine.data.Multi;

public class MultiApi extends BaseApi {
	public Multi[] getMyMultis(){
		throw new UnimplementedException();
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
