package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Wrapper class for GSON
 * @see StyleSheetData
 */
public class StyleSheet extends Thing<StyleSheetData> {
	public static StyleSheet fromInputStream(InputStream is) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is, writer, "UTF-8");
		return fromString(writer.toString());
	}

	private static StyleSheet fromString(String json) {
		Gson gson = new GsonBuilder().create();
		StyleSheet retval = gson.fromJson(json, StyleSheet.class);
		return retval;
	}
}
