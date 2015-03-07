package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.fizzl.redditengine.data.type.EditedType;
import net.fizzl.redditengine.data.type.LikedType;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class is a Listing wrapper that contains comments. An instance of this class can be created from an InputStream.
 * @see CommentListingData
 * @see java.io.InputStream
 */
public class CommentListing extends Listing<CommentListingData> {

	/**
	 * Returns a {@link #CommentListing} from an InputStream
	 * 
	 * @param is	InputStream containing a comment listing as JSON
	 * @return		CommentListing
	 * @throws 		IOException
	 * @throws		JSONException
	 */
	public static CommentListing fromInputStream(InputStream is) throws IOException, JSONException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is, writer, "UTF-8");
		return fromString(writer.toString());
	}
	
	/**
	 * Returns a {@link #CommentListing} from a JSON string
	 * 
	 * @param str	a comment listing as JSON
	 * @return		CommentListing
	 */
	public static CommentListing fromString(String str) throws JSONException {
		GsonBuilder builder = new GsonBuilder();
		
		// regular type adapters
		builder.registerTypeAdapter(EditedType.class, new EditedType.TypeAdapter());
		builder.registerTypeAdapter(LikedType.class, new LikedType.TypeAdapter());
		builder.registerTypeAdapter(Comment.class, new Comment.TypeAdapter());
		// special type adapter to pick a t1 listing from the response array
		builder.registerTypeAdapter(CommentListingArray.class, new CommentListingArray.TypeAdapter());
		// everything must be registered before create()
		
		Gson gson = builder.create();		
		CommentListingArray arrayResponse = gson.fromJson(str, CommentListingArray.class);
		
		return arrayResponse.getCommentListing();
	}

}
