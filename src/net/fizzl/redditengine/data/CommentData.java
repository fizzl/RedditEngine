package net.fizzl.redditengine.data;

import net.fizzl.redditengine.data.type.EditedType;


public class CommentData {
	String subreddit_id;
	String banned_by;
	String subreddit;
	int likes;
	CommentListing replies;
	boolean saved;
	String id;
	int gilded;
	String author;
	String parent_id;
	String approved_by;
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
}
