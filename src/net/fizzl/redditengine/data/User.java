package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;

public class User extends Thing<UserData> {
	public static User fromInputStream(InputStream is) throws IOException {
		return GsonTemplate.fromInputStream(is, User.class);
	}

	public static User fromString(String str) {
		return GsonTemplate.fromString(str, User.class);
	}
}
