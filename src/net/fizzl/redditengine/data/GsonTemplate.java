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
