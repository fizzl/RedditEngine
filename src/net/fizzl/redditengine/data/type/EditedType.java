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

/**
 * A class for representing the <tt>edited</tt> field in the JSON representation
 * @see TypeAdapter
 */
public class EditedType implements Serializable {
	private static final long serialVersionUID = 6801282971549899590L;
	boolean edited = false;
	double editTime = 0;
	
	/**
	 * A class for serializing and deserializing to and from the <tt>edited</tt> field in JSON
	 */
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
				if (!raw.equals("true")) {
					// normally raw should be a double if raw is not "false"
					ret.editTime = Double.parseDouble(raw);
				} else {
					// TODO if raw is just "true", what should editTime be?
				}
			}
			return ret;
		}
	}
}
