package net.fizzl.redditengine.data;

import net.fizzl.redditengine.data.type.LikedType;

public class AwardData {
	String domain;
	String banned_by;
	MediaEmbed media_embed;
	MediaEmbed secure_media_embed;
	String subreddit;
	String selftext_html;
	String selftext;
	LikedType likes;
	String title;
	String secure_media;
	String link_flair_text;
	String author_flair_text;
	String id;
	String name;
	String subreddit_id;
	int gilded;
	boolean clicked;
	boolean stickied;
	String author;
	int num_comments;
	int score;
	String approved_by;
	boolean over_18;
	boolean hidden;
	String thumbnail;
	boolean edited;
	String link_flair_css_class;
	String author_flair_css_class;
	int downs;
	boolean saved;
	boolean is_self;
	String permalink;
	double created;
	double created_utc;
	String url;
	String distinguished;
	String media;
	boolean visited;
	String num_reports;
	int ups;
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getBanned_by() {
		return banned_by;
	}
	public void setBanned_by(String banned_by) {
		this.banned_by = banned_by;
	}
	public MediaEmbed getMedia_embed() {
		return media_embed;
	}
	public void setMedia_embed(MediaEmbed media_embed) {
		this.media_embed = media_embed;
	}
	public MediaEmbed getSecure_media_embed() {
		return secure_media_embed;
	}
	public void setSecure_media_embed(MediaEmbed secure_media_embed) {
		this.secure_media_embed = secure_media_embed;
	}
	public String getSubreddit() {
		return subreddit;
	}
	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}
	public String getSelftext_html() {
		return selftext_html;
	}
	public void setSelftext_html(String selftext_html) {
		this.selftext_html = selftext_html;
	}
	public String getSelftext() {
		return selftext;
	}
	public void setSelftext(String selftext) {
		this.selftext = selftext;
	}
	public LikedType getLikes() {
		return likes;
	}
	public void setLikes(LikedType likes) {
		this.likes = likes;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSecure_media() {
		return secure_media;
	}
	public void setSecure_media(String secure_media) {
		this.secure_media = secure_media;
	}
	public String getLink_flair_text() {
		return link_flair_text;
	}
	public void setLink_flair_text(String link_flair_text) {
		this.link_flair_text = link_flair_text;
	}
	public String getAuthor_flair_text() {
		return author_flair_text;
	}
	public void setAuthor_flair_text(String author_flair_text) {
		this.author_flair_text = author_flair_text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubreddit_id() {
		return subreddit_id;
	}
	public void setSubreddit_id(String subreddit_id) {
		this.subreddit_id = subreddit_id;
	}
	public int getGilded() {
		return gilded;
	}
	public void setGilded(int gilded) {
		this.gilded = gilded;
	}
	public boolean isClicked() {
		return clicked;
	}
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	public boolean isStickied() {
		return stickied;
	}
	public void setStickied(boolean stickied) {
		this.stickied = stickied;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getNum_comments() {
		return num_comments;
	}
	public void setNum_comments(int num_comments) {
		this.num_comments = num_comments;
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
	public boolean isOver_18() {
		return over_18;
	}
	public void setOver_18(boolean over_18) {
		this.over_18 = over_18;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public boolean isEdited() {
		return edited;
	}
	public void setEdited(boolean edited) {
		this.edited = edited;
	}
	public String getLink_flair_css_class() {
		return link_flair_css_class;
	}
	public void setLink_flair_css_class(String link_flair_css_class) {
		this.link_flair_css_class = link_flair_css_class;
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
	public boolean isSaved() {
		return saved;
	}
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	public boolean isIs_self() {
		return is_self;
	}
	public void setIs_self(boolean is_self) {
		this.is_self = is_self;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	public double getCreated() {
		return created;
	}
	public void setCreated(double created) {
		this.created = created;
	}
	public double getCreated_utc() {
		return created_utc;
	}
	public void setCreated_utc(double created_utc) {
		this.created_utc = created_utc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDistinguished() {
		return distinguished;
	}
	public void setDistinguished(String distinguished) {
		this.distinguished = distinguished;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public String getNum_reports() {
		return num_reports;
	}
	public void setNum_reports(String num_reports) {
		this.num_reports = num_reports;
	}
	public int getUps() {
		return ups;
	}
	public void setUps(int ups) {
		this.ups = ups;
	}
}
