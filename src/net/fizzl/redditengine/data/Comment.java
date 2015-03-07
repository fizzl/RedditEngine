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
