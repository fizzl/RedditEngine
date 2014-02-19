package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.fizzl.redditengine.data.type.EditedType;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class is a Listing Thing that contains subreddits and methods to create them from an InputStream
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
