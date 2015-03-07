package net.fizzl.redditengine.impl;


public class UnexpectedHttpResponseException extends Exception {
	public UnexpectedHttpResponseException(String detailMessage) {
		super(detailMessage);
	}
	public UnexpectedHttpResponseException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
	public UnexpectedHttpResponseException(Throwable throwable) {
		super(throwable);
	}
	private static final long serialVersionUID = 1L;
}
