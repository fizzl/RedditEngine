package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

/**
 * A wrapper class for GSON
 * @see SubredditData
 */
public class Subreddit extends Thing<SubredditData> {
	/**
	 * Returns a {@link Subreddit} from in InputStream
	 * 
	 * @param is	InputStream containing a Subreddit as JSON
	 * @return	Subreddit
	 * @throws IOException
	 */
	public static Subreddit fromInputStream(InputStream is) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromString(writer.toString());
	}

	/**
	 * Returns a {@link Subreddit} from a JSON String
	 * 
	 * @param string	Subreddit as JSON
	 * @return Subreddit
	 */
	private static Subreddit fromString(String string) {
		Gson gson = new Gson();
		Subreddit ret = gson.fromJson(string, Subreddit.class);
		return ret;
	}
}
