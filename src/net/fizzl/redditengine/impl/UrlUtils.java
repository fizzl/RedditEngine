package net.fizzl.redditengine.impl;

/**
 * This is a helper class that specifies the base URL for the reddit API
 */
public class UrlUtils {
	public static final String BASE_URL = "http://reddit.local";
	public static final String REDDIT_SSL = BASE_URL;
	public static final String MODE_URL = ".json";
	public static final String X_MODHASH = "X-Modhash";

	/**
	 * Returns a reddit API URL
	 * 
	 * @param place path for the URL
	 * @return URL as String
	 */
	public static String getGetUrl(String place) {
		StringBuilder sb = new StringBuilder(BASE_URL);
		sb.append(place);
		//sb.append("/");
		sb.append(MODE_URL);
		return sb.toString();
	}
	
	/**
	 * Returns a comma delimited list from an array of Strings
	 */
	public static String toCommaDelimited(String[] strings) {
		StringBuilder builder = new StringBuilder();
		for (String string: strings) {
			if (builder.length() > 0) {
				builder.append(',');
			}
			builder.append(string);
		}
		return builder.toString();
	}
}
