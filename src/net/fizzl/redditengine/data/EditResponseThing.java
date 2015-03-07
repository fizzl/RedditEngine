package net.fizzl.redditengine.data;

/**
 * Edit response and its data
 * 
 * @see EditThingData
 */
public class EditResponseThing {
	Thing<EditThingData>[] things;
	
	public class EditThingData {
		String content;
		String sr;
		String id;
		
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getSr() {
			return sr;
		}
		public void setSr(String sr) {
			this.sr = sr;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	}
	
	public Thing<EditThingData>[] getThings() {
		return things;
	}
	public void setThings(Thing<EditThingData>[] things) {
		this.things = things;
	}
}
