package net.fizzl.redditengine.engine;

import java.io.IOException;

public class UnexpectedHttpResponseException extends IOException {
	@Override
	public String getLocalizedMessage() {
		return message;
	}
	@Override
	public String getMessage() {
		return message;
	}
	public UnexpectedHttpResponseException(String message) {
		this.message = message;
	}
	
	private String message;
	private static final long serialVersionUID = 1L;
}
