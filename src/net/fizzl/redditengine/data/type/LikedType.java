package net.fizzl.redditengine.data.type;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LikedType {
	boolean liked_boolean = false;
	int liked = 0;
	public static class TypeAdapter implements JsonSerializer<LikedType>, JsonDeserializer<LikedType> {
		@Override
		public JsonElement serialize(LikedType src, Type typeOfSrc, JsonSerializationContext context) {
			if(src.liked_boolean == true) {
				return new JsonPrimitive(src.liked_boolean);
			}
			return new JsonPrimitive(src.liked);
		}
	
		@Override
		public LikedType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			LikedType ret = new LikedType();
			String raw = json.getAsJsonPrimitive().getAsString();
			if (!raw.equals("false")) {
				ret.liked_boolean = true;
				if (!raw.equals("true")) {
					ret.liked = Integer.parseInt(raw);
				} else {
					// TODO if raw is just "true", what should liked be?
				}
			}
			return ret;
		}
	}
}
