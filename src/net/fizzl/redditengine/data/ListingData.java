package net.fizzl.redditengine.data;

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
