package net.fizzl.redditengine.data;

import java.lang.reflect.Type;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * A wrapper and filter class for comment listing response. Contains a {@link CommentListing}.
 * Uses TypeAdapter as a filter.
 */
// TODO is there a smarter way to filter data?
public class CommentListingArray {
	public CommentListingArray(CommentListing content){
		this.content = content;
	}
	public CommentListing getCommentListing() {
		return content;
	}
	private final CommentListing content;
	
	public static class TypeAdapter implements JsonDeserializer<CommentListingArray> {
		@Override
		public CommentListingArray deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			CommentListing commentListing = null;
			JsonArray array = json.getAsJsonArray();
			
			// should always get an array with two elements
			if (array.size() != 2) {
				throw new JsonParseException(json.toString());
			}
			
			// first cell is always t3, second is comments (might be empty)
			JsonObject cell = array.get(1).getAsJsonObject();
			commentListing = context.deserialize(cell, CommentListing.class);
			
			// check if second cell has a t1 listing
			JsonObject data = cell.getAsJsonObject().get("data").getAsJsonObject();			
			JsonArray children = data.getAsJsonArray("children");
			if (children.size() > 0) {
				JsonObject first = children.get(0).getAsJsonObject();
				String kind = first.getAsJsonPrimitive("kind").getAsString();
				if (!kind.equals("t1")) {
					Log.w(getClass().getName(), " kind is not t1 " + first);
				}
			} else {
				Log.w(getClass().getName(), " missing data " + data);
			}			
						
			return new CommentListingArray(commentListing);
		}
	}
	
}