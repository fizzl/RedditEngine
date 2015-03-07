package net.fizzl.redditengine.data;

/**
 * t4 Message
 */
public class MessageData {
	String body;
	String id;
	String name;  // fullname
	String subject;
	String body_html;
	String dest;  // receiver
	String author;  // sender
	boolean was_comment;
	Object first_message;  // TODO what should this type be
	Object first_message_name;  // TODO what should this type be
	double created;  // TODO should this be EditedType instead?
	double created_utc;
	Object subreddit;  // TODO what should this type be
	Object parent_id;  // TODO what should this type be
	String context;
	String replies;
	boolean New;  // can't have a field called "new", will this work? if not, use JsonDeserializer 
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public boolean isWas_comment() {
		return was_comment;
	}
	public void setWas_comment(boolean was_comment) {
		this.was_comment = was_comment;
	}
	public Object getFirst_message() {
		return first_message;
	}
	public void setFirst_message(Object first_message) {
		this.first_message = first_message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getFirst_message_name() {
		return first_message_name;
	}
	public void setFirst_message_name(Object first_message_name) {
		this.first_message_name = first_message_name;
	}
	public double getCreated() {
		return created;
	}
	public void setCreated(double created) {
		this.created = created;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getCreated_utc() {
		return created_utc;
	}
	public void setCreated_utc(double created_utc) {
		this.created_utc = created_utc;
	}
	public String getBody_html() {
		return body_html;
	}
	public void setBody_html(String body_html) {
		this.body_html = body_html;
	}
	public Object getSubreddit() {
		return subreddit;
	}
	public void setSubreddit(Object subreddit) {
		this.subreddit = subreddit;
	}
	public Object getParent_id() {
		return parent_id;
	}
	public void setParent_id(Object parent_id) {
		this.parent_id = parent_id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getReplies() {
		return replies;
	}
	public void setReplies(String replies) {
		this.replies = replies;
	}
	public boolean isNew() {
		return New;
	}
	public void setNew(boolean new1) {
		New = new1;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
