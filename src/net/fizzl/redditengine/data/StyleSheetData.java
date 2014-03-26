package net.fizzl.redditengine.data;

/**
 * subreddit's current stylesheet 
 */
public class StyleSheetData {
	Image[] images;
	String subreddit_id;
	String prevstyle;
	String stylesheet;

	public Image[] getImages() {
		return images;
	}
	public void setImages(Image[] images) {
		this.images = images;
	}
	public String getSubreddit_id() {
		return subreddit_id;
	}
	public void setSubreddit_id(String subreddit_id) {
		this.subreddit_id = subreddit_id;
	}
	public String getPrevstyle() {
		return prevstyle;
	}
	public void setPrevstyle(String prevstyle) {
		this.prevstyle = prevstyle;
	}
	public String getStylesheet() {
		return stylesheet;
	}
	public void setStylesheet(String stylesheet) {
		this.stylesheet = stylesheet;
	}
}
