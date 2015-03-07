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

public class User extends Thing<UserData> {
	public static User fromInputStream(InputStream is) throws IOException {
		return GsonTemplate.fromInputStream(is, User.class);
	}

	public static User fromString(String str) {
		return GsonTemplate.fromString(str, User.class);
	}
}
