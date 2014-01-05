package net.fizzl.redditengine;

public class UrlUtils {
	public static final String BASE_URL = "http://www.reddit.com";
	public static final String MODE_URL = ".json";

	public static String getUrl(String place) {
		StringBuilder sb = new StringBuilder(BASE_URL);
		sb.append(place);
		sb.append("/");
		sb.append(MODE_URL);
		return sb.toString();
	}
}
