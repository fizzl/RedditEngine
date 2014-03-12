package net.fizzl.redditengine.data;

public class JsonResponseData<T> extends Thing<T>{
	private String[][] errors;	
	
	public String[][] getErrors() {
		return errors;
	}
	
	public void setErrors(String[][] errors) {
		this.errors = errors;
	}
}
