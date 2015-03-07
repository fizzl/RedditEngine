package net.fizzl.redditengine.data;

/**
 * Holds all data in a Subreddit
 */
public class SubredditData {
	String submit_text_html;
	boolean user_is_banned;
	String id;
	String submit_text;
	String display_name;
	String header_img;
	String description_html;
	String title;
	boolean over18;
	boolean user_is_moderator;
	String header_title;
	String description;
	String submit_link_label;
	String accounts_active;
	boolean public_traffic;
	int[] header_size;
	long subscribers;
	String submit_text_label;
	String name;
	long created;
	String url;
	long created_utc;
	boolean user_is_contributor;
	String public_description;
	int comment_score_hide_mins;
	String subreddit_type;
	String submission_type;
	boolean user_is_subscriber;
	public String getSubmit_text_html() {
		return submit_text_html;
	}
	public void setSubmit_text_html(String submit_text_html) {
		this.submit_text_html = submit_text_html;
	}
	public boolean isUser_is_banned() {
		return user_is_banned;
	}
	public void setUser_is_banned(boolean user_is_banned) {
		this.user_is_banned = user_is_banned;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubmit_text() {
		return submit_text;
	}
	public void setSubmit_text(String submit_text) {
		this.submit_text = submit_text;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getHeader_img() {
		return header_img;
	}
	public void setHeader_img(String header_img) {
		this.header_img = header_img;
	}
	public String getDescription_html() {
		return description_html;
	}
	public void setDescription_html(String description_html) {
		this.description_html = description_html;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isOver18() {
		return over18;
	}
	public void setOver18(boolean over18) {
		this.over18 = over18;
	}
	public boolean isUser_is_moderator() {
		return user_is_moderator;
	}
	public void setUser_is_moderator(boolean user_is_moderator) {
		this.user_is_moderator = user_is_moderator;
	}
	public String getHeader_title() {
		return header_title;
	}
	public void setHeader_title(String header_title) {
		this.header_title = header_title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubmit_link_label() {
		return submit_link_label;
	}
	public void setSubmit_link_label(String submit_link_label) {
		this.submit_link_label = submit_link_label;
	}
	public String getAccounts_active() {
		return accounts_active;
	}
	public void setAccounts_active(String accounts_active) {
		this.accounts_active = accounts_active;
	}
	public boolean isPublic_traffic() {
		return public_traffic;
	}
	public void setPublic_traffic(boolean public_traffic) {
		this.public_traffic = public_traffic;
	}
	public int[] getHeader_size() {
		return header_size;
	}
	public void setHeader_size(int[] header_size) {
		this.header_size = header_size;
	}
	public long getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(long subscribers) {
		this.subscribers = subscribers;
	}
	public String getSubmit_text_label() {
		return submit_text_label;
	}
	public void setSubmit_text_label(String submit_text_label) {
		this.submit_text_label = submit_text_label;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCreated() {
		return created;
	}
	public void setCreated(long created) {
		this.created = created;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getCreated_utc() {
		return created_utc;
	}
	public void setCreated_utc(long created_utc) {
		this.created_utc = created_utc;
	}
	public boolean isUser_is_contributor() {
		return user_is_contributor;
	}
	public void setUser_is_contributor(boolean user_is_contributor) {
		this.user_is_contributor = user_is_contributor;
	}
	public String getPublic_description() {
		return public_description;
	}
	public void setPublic_description(String public_description) {
		this.public_description = public_description;
	}
	public int getComment_score_hide_mins() {
		return comment_score_hide_mins;
	}
	public void setComment_score_hide_mins(int comment_score_hide_mins) {
		this.comment_score_hide_mins = comment_score_hide_mins;
	}
	public String getSubreddit_type() {
		return subreddit_type;
	}
	public void setSubreddit_type(String subreddit_type) {
		this.subreddit_type = subreddit_type;
	}
	public String getSubmission_type() {
		return submission_type;
	}
	public void setSubmission_type(String submission_type) {
		this.submission_type = submission_type;
	}
	public boolean isUser_is_subscriber() {
		return user_is_subscriber;
	}
	public void setUser_is_subscriber(boolean user_is_subscriber) {
		this.user_is_subscriber = user_is_subscriber;
	}
}
