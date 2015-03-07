package net.fizzl.redditengine.data;

/**
 * Subreddit multi class
 */
public class MultiData {
	String[] fields;	// subreddit list
	String explanation;
	String reason;
	
	public String[] getFields() {
		return fields;
	}
	public void setFields(String[] fields) {
		this.fields = fields;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
