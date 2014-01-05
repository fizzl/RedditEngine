package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.fizzl.redditengine.data.type.EditedType;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommentListing extends Listing<CommentListingData> {

	public static CommentListing fromInputStream(InputStream is) throws IOException, JSONException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromString(writer.toString());
	}
	public static CommentListing fromString(String str) throws JSONException {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(EditedType.class, new EditedType.EditedTypeAdapter());
		Gson gson = builder.create();
		JSONArray arr = new JSONArray(str);
		
		CommentListing ret = gson.fromJson(arr.getJSONObject(1).toString(), CommentListing.class);
		return ret;
	}
}
