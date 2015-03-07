package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.fizzl.redditengine.data.type.EditedType;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ModlogListing extends Listing<ModlogListingData> {
	public static ModlogListing fromInputStream(InputStream is) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromString(writer.toString());
	}
	
	public static ModlogListing fromString(String str) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(EditedType.class, new EditedType.TypeAdapter());
		Gson gson = builder.create();
		ModlogListing ret = gson.fromJson(str, ModlogListing.class);
		return ret;
	}
}
