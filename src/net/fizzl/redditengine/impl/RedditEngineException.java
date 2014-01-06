package net.fizzl.redditengine.impl;

public class RedditEngineException extends Exception {
	public RedditEngineException(String detailMessage) {
		super(detailMessage);
	}
	public RedditEngineException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
	public RedditEngineException(Throwable throwable) {
		super(throwable);
	}
	private static final long serialVersionUID = 1L;
	
}
