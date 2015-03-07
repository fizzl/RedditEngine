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

/**
 * Comment posting response
 * @see CommentThingData
 */
public class CommentResponseThing {
	Thing<CommentThingData>[] things;
	
	/**
	 * Data of a comment posting response
	 */
	public class CommentThingData {
		// TODO make this an outer class?
		String parent;
		String content;
		String id;
		String contentText;
		String link;
		Object replies;  // TODO type
		String contentHTML;
		
		public String getParent() {
			return parent;
		}
		public void setParent(String parent) {
			this.parent = parent;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	}
	
	public Thing<CommentThingData>[] getThings() {
		return things;
	}
	public void setThings(Thing<CommentThingData>[] things) {
		this.things = things;
	}
}
