package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User extends Thing<UserData> {
	public static User fromInputStream(InputStream is) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromString(writer.toString());
	}

	public static User fromString(String str) {
		Gson gson = new GsonBuilder().create();
		User ret = gson.fromJson(str, User.class);
		return ret;
	}
}
