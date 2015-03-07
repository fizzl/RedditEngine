package net.fizzl.redditengine.data;

/**
 * Response from compose. Contains a captcha iden if required
 */
public class ComposeResponseJson {
	private String[][] errors;
	private String captcha;
	
	public String[][] getErrors() {
		return errors;
	}
	
	public void setErrors(String[][] errors) {
		this.errors = errors;
	}
	
	public String getCaptcha() {
		return captcha;
	}
	
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
