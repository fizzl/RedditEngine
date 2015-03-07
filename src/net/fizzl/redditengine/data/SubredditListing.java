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
 package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.fizzl.redditengine.data.type.EditedType;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class is a Listing wrapper that contains subreddits. An instance of this class can be created from an InputStream.
 * @see SubredditData
 * @see java.io.InputStream
 */
public class SubredditListing extends Listing<SubredditListingData> {
	/**
	 * Returns a {@link #SubredditListing} from an InputStream
	 * 
	 * @param is	InputStream containing a subreddit listing as JSON
	 * @return		SubredditListing
	 * @throws 		IOException
	 */
	public static SubredditListing fromInputStream(InputStream is) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromString(writer.toString());
	}
	
	/**
	 * Returns a {@link #SubredditListing} from a JSON string
	 * 
	 * @param str	a subreddit listing as JSON
	 * @return		SubredditListing
	 */
	public static SubredditListing fromString(String str) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(EditedType.class, new EditedType.TypeAdapter());
		Gson gson = builder.create();
		SubredditListing ret = gson.fromJson(str, SubredditListing.class);
		return ret;
	}
}
