package net.fizzl.redditengine.data;

public class AuthJsonResponse extends Thing<AuthJsonResponseData> {	
	private String[][] errors;	
	
	public String[][] getErrors() {
		return errors;
	}

	public void setErrors(String[][] errors) {
		this.errors = errors;
	}
}
