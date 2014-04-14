package net.fizzl.redditengine.data;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

/**
 * A wrapper class for GSON
 * @see CommentData
 */
public class Comment extends Thing<CommentData> {
	public static class TypeAdapter implements JsonDeserializer<Comment> {	
		@Override
		public Comment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			JsonObject jsonData = json.getAsJsonObject().get("data").getAsJsonObject();
			JsonElement replies = jsonData.get("replies");
			// sometimes replies is a CommentListing, sometimes "" (no comments)
			if (replies != null && replies.isJsonPrimitive()) {
				JsonPrimitive primitive = replies.getAsJsonPrimitive();
				if (primitive.isString()) {
					String replyString = primitive.getAsString();
					jsonData.remove("replies");
					// normally no replies means ""
					if (!replyString.isEmpty()) {
						// what to do with this?
						throw new JsonParseException(replyString);
					}
				}
			}
			Comment comment = new Comment();
			CommentData commentData = context.deserialize(jsonData, CommentData.class);
			comment.setData(commentData);
			JsonObject object = json.getAsJsonObject();
			if (object.has("kind")) {
				comment.setKind(object.get("kind").getAsString());
			}
			// TODO do comments have ids and names?
			if (object.has("name")) {
				comment.setName(object.get("name").getAsString());
			}
			if (object.has("id")) {
				comment.setId(object.get("id").getAsString());
			}
			return comment;
		}
	}
}
