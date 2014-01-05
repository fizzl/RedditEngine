package net.fizzl.redditengine;

import java.io.IOException;

import net.fizzl.redditengine.data.CommentListing;
import net.fizzl.redditengine.data.FlairListing;
import net.fizzl.redditengine.data.LinkListing;
import net.fizzl.redditengine.data.Me;
import net.fizzl.redditengine.data.MessageListing;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.content.Context;

public interface RedditApi {
	// Account
	public void clearSessions(String passwd);
	public void deleteUser(String user, String passwd, String message);
	public void login(String user, String passwd, boolean remember);
	public Me me();
	public void register(String user, String passwd1, String passwd2, boolean remember, String email, String captcha, String captcha_iden);
	public void updateUser(String passwd, String email, String newpass1, String newpass2, boolean verify);
	
	// Apps
	public void addDeveloper(String appId, String user);
	public void deleteApp(String appId);
	public void removeDeveloper(String appId, String user);
	public void revokeApp(String appId);
	public void setAppIcon(String appId, String fileName);
	public void updateApp(String appId, String appName, String aboutUrl, String iconUrl);
	
	// Captcha
	public boolean needCaptcha();
	public String newCaptcha();
	public String captchaImageUrl(String captchaIdentity);
	
	// Flair
	public void clearFlairTemplates(String subreddit, String flair_type);
	public void deleteFlair(String subreddit, String name);
	public void deleteFlairTemplate(String subreddit, String templateId);
	public void flair(String subreddit, String name, String link, String text, String cssClass);
	public void flairConfig(String subreddit, boolean enabled, String flairPosition, 
			boolean flairSelfAssignEnabled, String linkFlairPosition, boolean linkFlairSelfAssignEnabled);
	public void flairCsv(String subreddit, String flairCsv);
	public FlairListing getFlairListing(String subreddit, String before, String after, int count, int limit, String name);
	public void flairTemplate(String subreddit, String cssClass, String flairTemplateId, String flairType, String text, boolean textEditable);
	public void selectFlair(String subreddit, String flairTemplateId, String link, String name, String text);
	public void setFlairEnabled(String subreddit, boolean enabled);
	
	// Links & Comments
	public void comment(String parentId, String text);
	public void delete(String thingId);
	public void edit(String thingId, String text);
	public void hide(String thingId);
	public void unhide(String thingId);
	public void info(String url, String thingId, int limit);
	public void markNSFW(String thingId);
	public void unmarkNSFW(String thingId);
	public CommentListing moreChildren(String linkId, String children, String moreId, String sort);
	public void report(String thingId);
	public void save(String thingId);
	public void unsave(String thingId);
	public void setContestMode(String thingId, boolean state);
	public void setSticky(String thingId, boolean state);
	public void submit(String subreddit, String title, String kind, String url, String text, 
			String captcha, String captchaIden, boolean resubmit, boolean save, boolean sendReplies);
	public void vote(String thingId, int dir);
	
	// Listings
	public LinkListing getLinkListingByName(String name);
	public LinkListing getLinkListing(String subreddit, String order, String topScope, String before, String after, int limit) 
			 throws ClientProtocolException, IOException;
	public CommentListing getCommentListing(String subreddit, String article,
			 String comment, int context, int depth, int limit, String sort) 
			 throws ClientProtocolException, IOException, JSONException;
	
	// Private messages
	public void block(String thingId);
	public void compose(String to, String subject, String text, String captcha, String captchaIdentity);
	public void readMessage(String thingId);
	public void unreadMessage(String thingId);
	public MessageListing getMessageListing(String where, boolean mark, String mid, String before, 
			String after, int count, int limit, String show);
	
	// Moderation
	// Multis
	// Subreddits
	// Users
	// Wiki
	public void setContext(Context context);
	public Context getContext();
	
	// Order and sorting for listings
	public static final String LISTING_HOT = "hot";
	public static final String LISTING_NEW = "new";
	public static final String LISTING_OLD = "old";
	public static final String LISTING_TOP = "top";
	public static final String LISTING_RANDOM 			= "random";
	public static final String LISTING_CONTROVERSIAL 	= "controversial";
	public static final String LISTING_CONFIDENCE 		= "confidence";
	
	// Scope of top listings
	public static final String LISTING_SCOPE_HOUR	= "hour";
	public static final String LISTING_SCOPE_DAY	= "day";
	public static final String LISTING_SCOPE_WEEK	= "week";
	public static final String LISTING_SCOPE_MONTH	= "month";
	public static final String LISTING_SCOPE_YEAR	= "year";
	public static final String LISTING_SCOPE_ALL	= "all";
	
	
	// Flair flags
	public static final String FLAIR_TYPE_USER = "USER_FLAIR";
	public static final String FLAIR_TYPE_LINK = "LINK_FLAIR";
	public static final String FLAIR_POS_LEFT = "left";
	public static final String FLAIR_POS_RIGHT = "right";
	
	// Submit type
	public static final String SUBMIT_KIND_LINK = "link";
	public static final String SUBMIT_KIND_SELF = "self";
	
}
