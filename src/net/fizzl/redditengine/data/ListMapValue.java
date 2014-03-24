package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

/**
 * Converts a JSON data structure of a list of key-value pairs to a List of Strings using <tt>name</tt> as map key
 * 
 * @param is	InputStream containing the JSON array of {name, value}s
 * @param name	key for map values to be returned
 * @return values from maps
 */
public class ListMapValue {
	public static List<String> fromInputStream(InputStream is, String name) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromInputStream(writer.toString(), name);
	}
	
	public static List<Map<String,String>> fromInputStream(InputStream is) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromInputStream(writer.toString());
	}
	
	public static List<String> fromInputStream(String json, String name) {
		List<Map<String,String>> listmap = fromInputStream(json);
		List<String> retval = new ArrayList<String>();
		for (Map<String,String> map : listmap) {
			String value = (String) map.get(name);
			retval.add(value);
		}
		return retval;
	}

	public static List<Map<String,String>> fromInputStream(String json) {
		Gson gson = new Gson();
		ArrayList<Map<String,String>> retval = gson.fromJson(json, ArrayList.class);
		return retval;
	}
}
