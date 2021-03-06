/**
 * Copyright Maxpower Inc Finland (2014)
 *
 * This file is part of RedditEngine.
 *
 * RedditEngine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RedditEngine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with RedditEngine.  If not, see <http://www.gnu.org/licenses/>.
 **/
 package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

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
		return GsonTemplate.fromInputStream(is, List.class);
	}
	
	public static List<String> fromInputStream(String json, String name) {
		List<Map<String,String>> listmap = fromString(json);
		List<String> retval = new ArrayList<String>();
		for (Map<String,String> map : listmap) {
			String value = (String) map.get(name);
			retval.add(value);
		}
		return retval;
	}

	public static List<Map<String,String>> fromString(String json) {
		return GsonTemplate.fromString(json, List.class);
	}
}
