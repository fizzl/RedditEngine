package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

/**
 * An utility class to get an instance of class T from InputStream
 * Need extra parameter because of type erasure
 */
public class GsonTemplate {
	public static <T> T fromInputStream(InputStream is, Class<T> type) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromString(writer.toString(), type);
	}
	
	public static <T> T fromString(String json, Class<T> type) {
		Gson gson = new Gson();
		T ret = gson.fromJson(json, type);
		return ret;
	}
}
