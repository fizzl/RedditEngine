package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;

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
		return GsonTemplate.fromInputStream(is, Subreddit.class);
	}
}
