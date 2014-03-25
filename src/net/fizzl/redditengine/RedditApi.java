package net.fizzl.redditengine;

import net.fizzl.redditengine.data.AuthResponse;
import net.fizzl.redditengine.data.CommentListing;
import net.fizzl.redditengine.data.FlairListing;
import net.fizzl.redditengine.data.JsonResponse;
import net.fizzl.redditengine.data.LinkListing;
import net.fizzl.redditengine.data.MessageListing;
import net.fizzl.redditengine.data.ModlogListing;
import net.fizzl.redditengine.data.Multi;
import net.fizzl.redditengine.data.Subreddit;
import net.fizzl.redditengine.data.SubredditListing;
import net.fizzl.redditengine.data.SubredditSettings;
import net.fizzl.redditengine.data.User;
import net.fizzl.redditengine.data.UserListing;
import net.fizzl.redditengine.impl.RedditEngineException;
import android.content.Context;

/**
 * The interface of RedditEngine. Contains the reddit API.
 * 
 * @see <a href="http://www.reddit.com/dev/api">http://www.reddit.com/dev/api</a>
 */
public interface RedditApi {
	// Account
	public JsonResponse<?> clearSessions(String passwd) throws RedditEngineException;
	public void deleteUser(String user, String passwd, String message) throws RedditEngineException;
	public AuthResponse login(String user, String passwd, boolean remember) throws RedditEngineException;
	public User me() throws RedditEngineException;
	public void register(String user, String passwd1, String passwd2, boolean remember, String email, String captcha, String captcha_iden) throws RedditEngineException;
	public void updateUser(String passwd, String email, String newpass1, String newpass2, boolean verify) throws RedditEngineException;
	
	// Apps
	public void addDeveloper(String appId, String user) throws RedditEngineException;
	public void deleteApp(String appId) throws RedditEngineException;
	public void removeDeveloper(String appId, String user) throws RedditEngineException;
	public void revokeApp(String appId) throws RedditEngineException;
	public void setAppIcon(String appId, String fileName) throws RedditEngineException;
	public void updateApp(String appId, String appName, String aboutUrl, String iconUrl) throws RedditEngineException;
	
	// Captcha
	public boolean needCaptcha() throws RedditEngineException;
	public String newCaptcha() throws RedditEngineException;
	public String captchaImageUrl(String captchaIdentity) throws RedditEngineException;
	
	// Flair
	public void clearFlairTemplates(String subreddit, String flair_type) throws RedditEngineException;
	public void deleteFlair(String subreddit, String name) throws RedditEngineException;
	public void deleteFlairTemplate(String subreddit, String templateId) throws RedditEngineException;
	public void flair(String subreddit, String name, String link, String text, String cssClass) throws RedditEngineException;
	public void flairConfig(String subreddit, boolean enabled, String flairPosition, 
			boolean flairSelfAssignEnabled, String linkFlairPosition, boolean linkFlairSelfAssignEnabled) throws RedditEngineException;
	public void flairCsv(String subreddit, String flairCsv) throws RedditEngineException;
	public FlairListing getFlairListing(String subreddit, String before, String after, int count, int limit, String name) throws RedditEngineException;
	public void flairTemplate(String subreddit, String cssClass, String flairTemplateId, String flairType, String text, boolean textEditable) throws RedditEngineException;
	public void selectFlair(String subreddit, String flairTemplateId, String link, String name, String text) throws RedditEngineException;
	public void setFlairEnabled(String subreddit, boolean enabled) throws RedditEngineException;
	
	// Links & Comments
	public void comment(String parentId, String text) throws RedditEngineException;
	public void delete(String thingId) throws RedditEngineException;
	public void edit(String thingId, String text) throws RedditEngineException;
	public void hide(String thingId) throws RedditEngineException;
	public void unhide(String thingId) throws RedditEngineException;
	public void info(String url, String thingId, int limit) throws RedditEngineException;
	public void markNSFW(String thingId) throws RedditEngineException;
	public void unmarkNSFW(String thingId) throws RedditEngineException;
	public CommentListing moreChildren(String linkId, String children, String moreId, String sort) throws RedditEngineException;
	public void report(String thingId) throws RedditEngineException;
	public void save(String thingId) throws RedditEngineException;
	public void unsave(String thingId) throws RedditEngineException;
	public void setContestMode(String thingId, boolean state) throws RedditEngineException;
	public void setSticky(String thingId, boolean state) throws RedditEngineException;
	public void submit(String subreddit, String title, String kind, String url, String text, 
			String captcha, String captchaIden, boolean resubmit, boolean save, boolean sendReplies) throws RedditEngineException;
	public void vote(String thingId, int dir) throws RedditEngineException;
	
	// Listings
	public LinkListing getLinkListingByName(String name) throws RedditEngineException;
	public LinkListing getLinkListing(String subreddit, String order, String topScope, String before, String after, int limit) throws RedditEngineException;
	public CommentListing getCommentListing(String subreddit, String article,
			 String comment, int context, int depth, int limit, String sort) throws RedditEngineException;
	
	// Private messages
	public void block(String thingId) throws RedditEngineException;
	public void compose(String to, String subject, String text, String captcha, String captchaIdentity) throws RedditEngineException;
	public void readMessage(String thingId) throws RedditEngineException;
	public void unreadMessage(String thingId) throws RedditEngineException;
	public MessageListing getMessageListing(String where, boolean mark, String mid, String before, 
			String after, int count, int limit, String show) throws RedditEngineException;
	
	// Moderation
	public ModlogListing getModlogListing(String subreddit, String type, String mod, String before, String after, int count, int limit) throws RedditEngineException;
	public void acceptModeratorInvite(String subreddit) throws RedditEngineException;
	public void approve(String thingId) throws RedditEngineException;
	public void distinguish(String thingId, String how) throws RedditEngineException;
	public void ignoreReports(String thingId) throws RedditEngineException;
	public void unignoreReports(String thingId) throws RedditEngineException;
	public void leaveContributor(String thingId) throws RedditEngineException;
	public void leaveModerator(String thingId) throws RedditEngineException;
	public void remove(String thing, boolean spam) throws RedditEngineException;
	public void getStyleSheet(String subreddit) throws RedditEngineException;
	
	// Multis
	public Multi[] getMyMultis() throws RedditEngineException;
	public void deleteMulti(String path) throws RedditEngineException;
	public Multi getMulti(String path) throws RedditEngineException;
	public void createOrEditMulti(String path, String[] subreddits, boolean isPublic) throws RedditEngineException;
	public void copyMulti(String path, String from, String to) throws RedditEngineException;
	public String getMultiDescription(String path) throws RedditEngineException;
	public void setMultiDescription(String path, String text) throws RedditEngineException;
	public void removeSubredditFromMulti(String path, String subreddit) throws RedditEngineException;
	public void addSubredditToMulti(String path, String subreddit) throws RedditEngineException;
	public String getSubredditInfoInMulti(String path, String subreddit) throws RedditEngineException;
	public void renameMulti(String path, String from, String to) throws RedditEngineException;
	
	// Search
	public LinkListing search(String subreddit, String query, boolean restrictToSubreddit, String before, String after, int count, 
			int limit, String sort, String timeScope, String show) throws RedditEngineException;

	// Subreddits
	public void deleteSubredditHeader(String subreddit) throws RedditEngineException;
	public void deleteSubredditImage(String subreddit, String imageName) throws RedditEngineException;
	public String[] getSubredditRecomendations(String[] reddits, String[] omit) throws RedditEngineException;
	public String[] searchSubreddits(String startswith, boolean withNSFW) throws RedditEngineException;
	public void siteAdmin(String name, SubredditSettings settings) throws RedditEngineException;
	public String getSubredditSubmitText(String subreddit) throws RedditEngineException;
	public String getSubredditStylesheet(String subreddit, String operation, String revid, String contest) throws RedditEngineException;
	public String[] getSubredditsByTopic(String query) throws RedditEngineException;
	public void subscribeSubreddit(String subreddit) throws RedditEngineException;
	public void unsubscribeSubreddit(String subreddit) throws RedditEngineException;
	public Subreddit aboutSubreddit(String subreddit) throws RedditEngineException;
	public SubredditSettings getSubredditSettings(String subreddit) throws RedditEngineException;
	public SubredditListing getMySubreddits(String where, String before, String after, int count, int limit, String show) throws RedditEngineException;
	public SubredditListing searchSubreddits(String query, String before, String after, int count, int limit, String show) throws RedditEngineException;
	public SubredditListing getSubreddits(String which, String before, String after, int count, int limit, String show) throws RedditEngineException;

	// Users
	public void friend(String user, String container, String type, String permissions, String note) throws RedditEngineException;
	public void unfriend(String user, String thingId, String container, String type) throws RedditEngineException;
	public void setPermissions(String user, String subreddit, String permissions) throws RedditEngineException;
	public boolean isUsernameAvailable(String user) throws RedditEngineException;
	public User aboutUser(String user) throws RedditEngineException;
	public UserListing getUserListing(String user, String what) throws RedditEngineException;
	
	// Wiki
	public void addWikiEditor(String subreddit, String page, String user) throws RedditEngineException;
	public void removeWikiEditor(String subreddit, String page, String user) throws RedditEngineException;
	public void editWiki(String subreddit, String page, String prevRevision, String reaason) throws RedditEngineException;
	public void hideWiki(String subreddit, String page) throws RedditEngineException;
	public void revertWiki(String subreddit, String page, String revision) throws RedditEngineException;
	
	// Android context
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
	
	// Moderation type
	public static final String MOD_TYPE_BANUSER				= "banuser";
	public static final String MOD_TYPE_UNBANUSER			= "unbanuser";
	public static final String MOD_TYPE_REMOVELINK			= "removelink";
	public static final String MOD_TYPE_APPROVELINK			= "approvelink";
	public static final String MOD_TYPE_REMOVECOMMENT		= "removecomment";
	public static final String MOD_TYPE_APPROVECOMMENT		= "approvecomment";
	public static final String MOD_TYPE_ADDMODERATOR		= "addmoderator";
	public static final String MOD_TYPE_INVITEMODERATOR		= "invitemoderator";
	public static final String MOD_TYPE_UNINVITEMODERATOR	= "uninvitemoderator";
	public static final String MOD_TYPE_ACCEPTMODERATORINVITE		= "acceptmoderatorinvite";
	public static final String MOD_TYPE_REMOVEMODERATOR		= "removemoderator";
	public static final String MOD_TYPE_ADDCONTRIBUTOR		= "addcontributor";
	public static final String MOD_TYPE_REMOVECONTRIBUTOR	= "removecontributor";
	public static final String MOD_TYPE_EDITSETTINGS		= "editsettings";
	public static final String MOD_TYPE_EDITFLAIR			= "editflair";
	public static final String MOD_TYPE_DISTINGUISH			= "distinguish";
	public static final String MOD_TYPE_MARKNSFW			= "marknsfw";
	public static final String MOD_TYPE_WIKIBANNED			= "wikibanned";
	public static final String MOD_TYPE_WIKICONTRIBUTOR		= "wikicontributor";
	public static final String MOD_TYPE_WIKIUNBANNED		= "wikiunbanned";
	public static final String MOD_TYPE_WIKIPAGELISTED		= "wikipagelisted";
	public static final String MOD_TYPE_REMOVEWIKICONTRIBUTOR	= "removewikicontributor";
	public static final String MOD_TYPE_WIKIREVISE			= "wikirevise";
	public static final String MOD_TYPE_WIKIPERMLEVEL		= "wikipermlevel";
	public static final String MOD_TYPE_IGNOREREPORTS		= "ignorereports";
	public static final String MOD_TYPE_UNIGNOREREPORTS		= "unignorereports";
	public static final String MOD_TYPE_SETPERMISSIONS		= "setpermissions";
	public static final String MOD_TYPE_STICKY				= "sticky";
	public static final String MOD_TYPE_UNSTICKY			= "unsticky";
	
	// Distinguishing a comment
	public static final String MOD_DISTINGUISH_YES		= "yes";
	public static final String MOD_DISTINGUISH_NO		= "no";
	public static final String MOD_DISTINGUISH_ADMIN	= "admin";
	public static final String MOD_DISTINGUISH_SPECIAL	= "special";
	
}
