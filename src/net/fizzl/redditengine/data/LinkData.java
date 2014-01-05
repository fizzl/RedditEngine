package net.fizzl.redditengine.data;

import net.fizzl.redditengine.data.type.EditedType;

public class LinkData {
	String domain;
	String banned_by;
	MediaEmbed media_embed;
	MediaEmbed secure_media_embed;
	Media media;
	Media secure_media;
	
	String subreddit;
	String selftext_html;
	String selftext;
	int likes;
	String link_flair_text;
	String id;
	
	boolean clicked;
	boolean stickied;
	String author;
	int score;
	String approved_by;
	boolean over_18;
	boolean hidden;
	String thumbnail;
	String subreddit_id;
	EditedType edited;
	String link_flair_css_class;
	String author_flair_css_class;
	int downs;
	boolean saved;
	boolean is_self;
	String permalink;
	String name;
	double created;
	String url;
	String author_flair_text;
	String title;
	double created_utc;
	int ups;
	int num_comments;
	boolean visited;
	int num_reports;
	String distinguished;
}
