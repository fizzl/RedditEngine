package net.fizzl.redditengine.data.type;

import java.io.Serializable;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LikedType implements Serializable {
	private static final long serialVersionUID = 7716692876769642655L;
	boolean liked_boolean = false;
	int liked = 0;
	public static class TypeAdapter implements JsonSerializer<LikedType>, JsonDeserializer<LikedType> {
		@Override
		public JsonElement serialize(LikedType src, Type typeOfSrc, JsonSerializationContext context) {
			// should serialize number first?
			if(src.liked > 0) {
				return new JsonPrimitive(src.liked);
			}
			return new JsonPrimitive(src.liked_boolean);
		}
	
		@Override
		public LikedType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			LikedType ret = new LikedType();
			String raw = json.getAsJsonPrimitive().getAsString();
			if (!raw.equals("false")) {
				if (!raw.equals("true")) {
					ret.liked = Integer.parseInt(raw);  // not false and not true, try number
				} else {
					ret.liked_boolean = true;
				}
			}
			return ret;
		}
	}
}
