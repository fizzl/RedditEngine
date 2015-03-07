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

import net.fizzl.redditengine.data.type.EditedType;
import net.fizzl.redditengine.data.type.LikedType;

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
		builder.registerTypeAdapter(LikedType.class, new LikedType.TypeAdapter());
		Gson gson = builder.create();
		LinkListing ret = gson.fromJson(str, LinkListing.class);
		return ret;
	}
}
