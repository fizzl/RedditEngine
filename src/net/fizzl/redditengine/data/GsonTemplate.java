package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

/**
 * An utility class to get an instance of {@link Class} T from {@link InputStream}.
 * </p>
 * Needs an extra parameter because of type erasure.
 */
public class GsonTemplate {
	/**
	 * Returns an instance of class T from {@link InputStream} that contains JSON data
	 * 
	 * @param is	JSON as InputStream
	 * @param type	{@link Class}<T> for conversion from JSON to POJO
	 * @return		POJO representation of the JSON data
	 * @throws IOException
	 * @see Gson
	 */
	public static <T> T fromInputStream(InputStream is, Class<T> type) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromString(writer.toString(), type);
	}
	
	/**
	 * Returns an instance of class T from UTF-8 String that contains JSON data
	 * 
	 * @param json	JSON as UTF-8 String
	 * @param type	{@link Class}<T> for conversion from JSON to POJO
	 * @return		POJO representation of the JSON data
	 * @throws IOException
	 * @see Gson
	 */
	public static <T> T fromString(String json, Class<T> type) {
		Gson gson = new Gson();
		T ret = gson.fromJson(json, type);
		return ret;
	}
}
