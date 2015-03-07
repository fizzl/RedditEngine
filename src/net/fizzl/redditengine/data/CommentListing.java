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
