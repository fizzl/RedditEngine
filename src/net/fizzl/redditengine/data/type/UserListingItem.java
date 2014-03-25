package net.fizzl.redditengine.data.type;

import java.lang.reflect.Type;

import net.fizzl.redditengine.data.Comment;
import net.fizzl.redditengine.data.Link;
import net.fizzl.redditengine.data.Thing;
import net.fizzl.redditengine.impl.UnimplementedException;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

/**
 * If Comment and Link have a common base class, use it as Thing type parameter
 */
public class UserListingItem extends Thing<Object> {
	public UserListingItem(Link link) {
		super();
		setData(link.getData());
		setId(link.getId());
		setKind(link.getKind());
		setName(link.getName());		
	}
	
	public UserListingItem(Comment comment) {
		super();
		setData(comment.getData());
		setId(comment.getId());
		setKind(comment.getKind());
		setName(comment.getName());
	}
	
	public static class TypeAdapter implements JsonSerializer<UserListingItem>, JsonDeserializer<UserListingItem> {
		@Override
		public JsonElement serialize(UserListingItem src, Type typeOfSrc, JsonSerializationContext context) {
			// TODO implementation
			throw new UnimplementedException();
		}

		@Override
		public UserListingItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			UserListingItem retval = null;
			if (json.isJsonObject()) {
				JsonObject obj = json.getAsJsonObject();
				if(!obj.get(KIND).isJsonNull()) {
					 String kind = obj.get(KIND).getAsString();
					 if (kind.equals("t1")) {
						 Comment comment = context.deserialize(obj, Comment.class);
						 // TODO smarter way to convert Comment to UserListingItem
						 retval = new UserListingItem(comment);
					 } else if (kind.equals("t3")) {
						 Link link = context.deserialize(obj, Link.class);
						 // TODO smarter way to convert Link to UserListingItem
						 retval = new UserListingItem(link);
					 }
				}
			}
			if (retval == null) {
				throw new JsonSyntaxException("Unable to parse " + typeOfT.getClass().getCanonicalName());
			}
			return retval;
		}		
	}
	
	private final static String KIND = "kind";
}
