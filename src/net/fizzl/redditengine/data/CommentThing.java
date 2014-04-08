package net.fizzl.redditengine.data;

/**
 * Comment posting response
 * @see CommentThingData
 */
public class CommentThing {
	Thing<CommentThingData>[] things;
	
	/**
	 * Data of a comment posting response
	 */
	public class CommentThingData {
		// TODO make this an outer class?
		String parent;
		String content;
		String id;
		
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
