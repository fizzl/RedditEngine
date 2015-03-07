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
	int liked = 0;
	public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	
	
	public static class TypeAdapter implements JsonSerializer<LikedType>, JsonDeserializer<LikedType> {
		@Override
		public JsonElement serialize(LikedType src, Type typeOfSrc, JsonSerializationContext context) {
			if(src.liked < 0) {
				return new JsonPrimitive(false);
			}
			if(src.liked > 0) {
				return new JsonPrimitive(true);
			}
			return new JsonPrimitive("null");
		}
	
		@Override
		public LikedType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			LikedType ret = new LikedType();
			String raw = json.getAsJsonPrimitive().getAsString();
			if(raw.equals("true")) {
				ret.liked = 1;
			} 
			else if(raw.equals("false")) {
				ret.liked = -1;
			}
			else if(raw.equals("null")) {
				ret.liked = 0;
			}
			else {
				ret.liked = Integer.parseInt(raw);
			}
			return ret;
		}
	}
}
