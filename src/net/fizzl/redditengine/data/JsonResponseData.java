package net.fizzl.redditengine.data;

/**
 * Json response that has errors and possibly a data field
 *
 * @param <T>	type of data in the data field (if any)
 */
public class JsonResponseData<T> extends Thing<T>{
	private String[][] errors;	
	
	public String[][] getErrors() {
		return errors;
	}
	
	public void setErrors(String[][] errors) {
		this.errors = errors;
	}
}
