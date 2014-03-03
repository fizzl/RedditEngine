package net.fizzl.redditengine.impl;

/**
 * This is a helper class that specifies the base URL for the reddit API
 */
public class UrlUtils {
	public static final String BASE_URL = "http://www.reddit.com";
	public static final String MODE_URL = ".json";

	public static String getGetUrl(String place) {
		StringBuilder sb = new StringBuilder(BASE_URL);
		sb.append(place);
		sb.append("/");
		sb.append(MODE_URL);
		return sb.toString();
	}
}
