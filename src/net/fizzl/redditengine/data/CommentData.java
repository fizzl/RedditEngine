package net.fizzl.redditengine.data;

import net.fizzl.redditengine.data.type.EditedType;
import net.fizzl.redditengine.data.type.LikedType;

/**
 * Holds all data in a reddit Comment.
 * May include other Comments.
 * @see CommentListing
 */
public class CommentData {
	String subreddit_id;
	String banned_by;
	String subreddit;
	LikedType likes;
	CommentListing replies;
	boolean saved;
	String id;
	int gilded;
	String author;
	String parent_id;
	int score;
	String approved_by;
	int controversiality;
	String body;
	EditedType edited;
	String author_flair_css_class;
	int downs;
	String body_html;
	String link_id;
	boolean score_hidden;
	String name;
	double created;
	String author_flair_text;
	double created_utc;
	String distinguished;
	int num_reports;
	int ups;
	
	public String getSubreddit_id() {
		return subreddit_id;
	}
	public void setSubreddit_id(String subreddit_id) {
		this.subreddit_id = subreddit_id;
	}
	public String getBanned_by() {
		return banned_by;
	}
	public void setBanned_by(String banned_by) {
		this.banned_by = banned_by;
	}
	public String getSubreddit() {
		return subreddit;
	}
	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}
	public LikedType getLikes() {
		return likes;
	}
	public void setLikes(LikedType likes) {
		this.likes = likes;
	}
	public CommentListing getReplies() {
		return replies;
	}
	public void setReplies(CommentListing replies) {
		this.replies = replies;
	}
	public boolean isSaved() {
		return saved;
	}
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getGilded() {
		return gilded;
	}
	public void setGilded(int gilded) {
		this.gilded = gilded;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public int getControversiality() {
		return controversiality;
	}
	public void setControversiality(int controversiality) {
		this.controversiality = controversiality;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public EditedType getEdited() {
		return edited;
	}
	public void setEdited(EditedType edited) {
		this.edited = edited;
	}
	public String getAuthor_flair_css_class() {
		return author_flair_css_class;
	}
	public void setAuthor_flair_css_class(String author_flair_css_class) {
		this.author_flair_css_class = author_flair_css_class;
	}
	public int getDowns() {
		return downs;
	}
	public void setDowns(int downs) {
		this.downs = downs;
	}
	public String getBody_html() {
		return body_html;
	}
	public void setBody_html(String body_html) {
		this.body_html = body_html;
	}
	public String getLink_id() {
		return link_id;
	}
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	public boolean isScore_hidden() {
		return score_hidden;
	}
	public void setScore_hidden(boolean score_hidden) {
		this.score_hidden = score_hidden;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCreated() {
		return created;
	}
	public void setCreated(double created) {
		this.created = created;
	}
	public String getAuthor_flair_text() {
		return author_flair_text;
	}
	public void setAuthor_flair_text(String author_flair_text) {
		this.author_flair_text = author_flair_text;
	}
	public double getCreated_utc() {
		return created_utc;
	}
	public void setCreated_utc(double created_utc) {
		this.created_utc = created_utc;
	}
	public String getDistinguished() {
		return distinguished;
	}
	public void setDistinguished(String distinguished) {
		this.distinguished = distinguished;
	}
	public int getNum_reports() {
		return num_reports;
	}
	public void setNum_reports(int num_reports) {
		this.num_reports = num_reports;
	}
	public int getUps() {
		return ups;
	}
	public void setUps(int ups) {
		this.ups = ups;
	}
}
