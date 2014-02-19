package net.fizzl.redditengine.data;

/**
 * Indefinite object for controlling pagination and filtering.
 * 
 * <tt>after</tt> / <tt>before</tt> - only one should be specified. these indicate the fullname of an item in the listing to use as the anchor point of the slice.
 * <tt>modhash</tt> - a token identifying a user.
 */
public class ListingData {
	String after;
	String before;
	String modhash;
	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public String getModhash() {
		return modhash;
	}
	public void setModhash(String modhash) {
		this.modhash = modhash;
	}
}
