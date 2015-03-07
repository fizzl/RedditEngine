/**
 * Copyright Maxpower Inc Finland (2014)
 *
 * This file is part of RedditEngine.
 *
 * RedditEngine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RedditEngine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with RedditEngine.  If not, see <http://www.gnu.org/licenses/>.
 **/
 package net.fizzl.redditengine.impl;

/**
 * This is a helper class that specifies the base URL for the reddit API
 */
public class UrlUtils {
	public static final String BASE_URL = "http://www.reddit.com";
	public static final String REDDIT_SSL = "https://ssl.reddit.com";
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
