package net.fizzl.redditengine.data.type;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EditedType {
	boolean edited = false;
	double editTime = 0;
	
	public static class TypeAdapter implements JsonSerializer<EditedType>,
			JsonDeserializer<EditedType> {
		@Override
		public JsonElement serialize(EditedType src, Type typeOfSrc,
				JsonSerializationContext context) {
			if(src.edited == true) {
				return new JsonPrimitive(src.editTime);
			}
			return new JsonPrimitive(false);
		}

		@Override
		public EditedType deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			EditedType ret = new EditedType();
			String raw = json.getAsJsonPrimitive().getAsString();
			if (!raw.equals("false")) {
				ret.edited = true;
				ret.editTime = Double.parseDouble(raw);
			}
			return ret;
		}
	}
}
