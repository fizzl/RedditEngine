package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.fizzl.redditengine.data.type.EditedType;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class is a Listing wrapper that contains links. An instance of this class can be created from an InputStream.
 * @see LinkListingData
 * @see java.io.InputStream
 */
public class LinkListing extends Listing<LinkListingData> {
	public static LinkListing fromInputStream(InputStream is) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromString(writer.toString());
	}
	
	public static LinkListing fromString(String str) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(EditedType.class, new EditedType.TypeAdapter());
		Gson gson = builder.create();
		LinkListing ret = gson.fromJson(str, LinkListing.class);
		return ret;
	}
}
