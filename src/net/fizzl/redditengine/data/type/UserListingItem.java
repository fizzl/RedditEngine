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
 package net.fizzl.redditengine.data.type;

import java.lang.reflect.Type;

import net.fizzl.redditengine.data.Award;
import net.fizzl.redditengine.data.AwardData;
import net.fizzl.redditengine.data.Comment;
import net.fizzl.redditengine.data.CommentData;
import net.fizzl.redditengine.data.Link;
import net.fizzl.redditengine.data.LinkData;
import net.fizzl.redditengine.data.Thing;
import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

/**
 * If Comment and Link have a common base class, use it as Thing type parameter
 */
public class UserListingItem extends Thing<Object> {
	public UserListingItem(Link link) {
		super();
		setData(link.getData());
		setId(link.getId());
		setKind(link.getKind());
		setName(link.getName());		
	}
	
	public UserListingItem(Comment comment) {
		super();
		setData(comment.getData());
		setId(comment.getId());
		setKind(comment.getKind());
		setName(comment.getName());
	}
	
	public UserListingItem(Award award) {
		super();
		setData(award.getData());
		setId(award.getId());
		setKind(award.getKind());
		setName(award.getName());
	}

	public Comment toComment() {
		if (this.getKind().equals("t1") == false) {
			Log.w(getClass().getName(), "Trying to convert a non-t1 type of data to CommentData");
		}
		Comment comment = new Comment();
		comment.setData((CommentData)this.getData());
		comment.setId(this.getId());
		comment.setName(this.getName());
		comment.setKind(this.getKind());
		return comment;
	}
	
	public Link toLink() {
		if (this.getKind().equals("t3") == false) {
			Log.w(getClass().getName(), "Trying to convert a non-t3 type of data to LinkData");
		}		
		Link link = new Link();
		link.setId(this.getId());
		link.setName(this.getName());
		link.setKind(this.getKind());
		link.setData((LinkData)this.getData());
		return link;
	}
	
	public Award toAward() {
		if (this.getKind().equals("t6") == false) {
			Log.w(getClass().getName(), "Trying to convert a non-t6 type of data to Award");
		}		
		Award award = new Award();
		award.setId(this.getId());
		award.setName(this.getName());
		award.setKind(this.getKind());
		award.setData((AwardData)this.getData());
		return award;
	}
	
	public static class TypeAdapter implements JsonSerializer<UserListingItem>, JsonDeserializer<UserListingItem> {
		@Override
		public JsonElement serialize(UserListingItem src, Type typeOfSrc, JsonSerializationContext context) {
			JsonElement retval = null;
			String kind = src.getKind();
			if (kind.equals("t1")) {
				Comment comment = src.toComment();
				retval = context.serialize(comment, Comment.class);
			} else if(kind.equals("t3")) {
				Link link = src.toLink();
				retval = context.serialize(link, Link.class);
			} else if(kind.equals("t6")) {
				Award award = src.toAward();
				retval = context.serialize(award, Award.class);
			} else {
				throw new JsonSyntaxException("Unable to serialize " + src);
			}
			return retval;
		}

		@Override
		public UserListingItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			UserListingItem retval = null;
			if (json.isJsonObject()) {
				JsonObject obj = json.getAsJsonObject();
				if(!obj.get(KIND).isJsonNull()) {
					 String kind = obj.get(KIND).getAsString();
					 // TODO smarter way to convert t1-t6 to UserListingItem
					 if (kind.equals("t1")) {
						 Comment comment = context.deserialize(obj, Comment.class);
						 retval = new UserListingItem(comment);
					 } else if (kind.equals("t3")) {
						 Link link = context.deserialize(obj, Link.class);
						 retval = new UserListingItem(link);
					 } else if (kind.equals("t6")) {
						 Award award = context.deserialize(obj, Award.class);
						 retval = new UserListingItem(award);
					 }
				}
			}
			if (retval == null) {
				throw new JsonSyntaxException("Unable to deserialize " + typeOfT.getClass().getCanonicalName());
			}
			return retval;
		}		
	}
	
	private final static String KIND = "kind";
}
