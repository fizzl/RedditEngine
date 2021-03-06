/**
 * Copyright Maxpower Inc Finland (2014)
 *
 * This file is part of RedditEngine.
 *
 * RedditEngine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RedditEngine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with RedditEngine.  If not, see <http://www.gnu.org/licenses/>.
 **/
 package net.fizzl.redditengine.impl;

import java.io.InputStream;

import net.fizzl.redditengine.RedditApi;
import net.fizzl.redditengine.data.AuthResponse;
import net.fizzl.redditengine.data.CommentListing;
import net.fizzl.redditengine.data.CommentResponse;
import net.fizzl.redditengine.data.ComposeResponse;
import net.fizzl.redditengine.data.EditResponse;
import net.fizzl.redditengine.data.FlairListing;
import net.fizzl.redditengine.data.JsonResponse;
import net.fizzl.redditengine.data.LabeledMulti;
import net.fizzl.redditengine.data.LinkListing;
import net.fizzl.redditengine.data.SubmitResponse;
import net.fizzl.redditengine.data.MessageListing;
import net.fizzl.redditengine.data.ModlogListing;
import net.fizzl.redditengine.data.StyleSheet;
import net.fizzl.redditengine.data.Subreddit;
import net.fizzl.redditengine.data.SubredditListing;
import net.fizzl.redditengine.data.SubredditSettings;
import net.fizzl.redditengine.data.User;
import net.fizzl.redditengine.data.UserListing;
import android.content.Context;

/**
 * This class implements the whole RedditApi interface
 */
public class DefaultRedditApi implements RedditApi {
	// Singleton
	private static RedditApi me;
	public static RedditApi getInstance() {
		if(me == null) {
			me = new DefaultRedditApi();
		}
		return me;
	}
	
	private DefaultRedditApi() {
		mAccountApi = new AccountApi();
		mAppsApi = new AppsApi();
		mCaptchaApi = new CaptchaApi();
		mFlairApi = new FlairApi();
		mLinkCommentApi = new LinkCommentApi();
		mListingsApi = new ListingsApi();
		mMessagesApi = new MessagesApi();
		mModerationApi = new ModerationApi();
		mMultiApi = new MultiApi();
		mSearchApi = new SearchApi();
		mSubredditsApi = new SubredditsApi();
		mUserApi = new UserApi();
		mWikiApi = new WikiApi();
	}

	@Override
	public void setContext(Context context) {
		mContext = context;
	}

	@Override
	public Context getContext() {
		return mContext;
	}

	private static Context mContext;

	@Override
	public JsonResponse<?> clearSessions(String passwd) throws RedditEngineException {
		return mAccountApi.clearSessions(passwd);
	}

	@Override
	public JsonResponse<?> deleteUser(String user, String passwd, String message) throws RedditEngineException {
		return mAccountApi.deleteUser(user, passwd, message);
	}

	@Override
	public AuthResponse login(String user, String passwd, boolean remember) throws RedditEngineException {
		return mAccountApi.login(user, passwd, remember);
	}

	@Override
	public void logout() {
		mAccountApi.logout();
	}

	@Override
	public User me() throws RedditEngineException {
		return mAccountApi.me();
	}

	@Override
	public AuthResponse register(String user, String passwd1, String passwd2,
			boolean remember, String email, String captcha, String captcha_iden) throws RedditEngineException {
		return mAccountApi.register(user, passwd1, passwd2, remember, email, captcha,
				captcha_iden);
	}

	@Override
	public JsonResponse<?> updateUser(String passwd, String email, String newpass1,
			String newpass2, boolean verify) throws RedditEngineException {
		return mAccountApi.updateUser(passwd, email, newpass1, newpass2, verify);
	}

	@Override
	public void addDeveloper(String appId, String user) {
		mAppsApi.addDeveloper(appId, user);
	}

	@Override
	public void deleteApp(String appId) {
		mAppsApi.deleteApp(appId);
	}

	@Override
	public void removeDeveloper(String appId, String user) {
		mAppsApi.removeDeveloper(appId, user);
	}

	@Override
	public void revokeApp(String appId) {
		mAppsApi.revokeApp(appId);
	}

	@Override
	public void setAppIcon(String appId, String fileName) {
		mAppsApi.setAppIcon(appId, fileName);
	}

	@Override
	public void updateApp(String appId, String appName, String aboutUrl,
			String iconUrl) {
		mAppsApi.updateApp(appId, appName, aboutUrl, iconUrl);
	}

	@Override
	public boolean needCaptcha() throws RedditEngineException {
		return mCaptchaApi.needCaptcha();
	}

	@Override
	public String newCaptcha() throws RedditEngineException {
		return mCaptchaApi.newCaptcha();
	}

	@Override
	public InputStream captchaImage(String captchaIdentity) throws RedditEngineException {
		return mCaptchaApi.captchaImageUrl(captchaIdentity);
	}

	@Override
	public void clearFlairTemplates(String subreddit, String flair_type) {
		mFlairApi.clearFlairTemplates(subreddit, flair_type);
	}

	@Override
	public void deleteFlair(String subreddit, String name) {
		mFlairApi.deleteFlair(subreddit, name);
	}

	@Override
	public void deleteFlairTemplate(String subreddit, String templateId) {
		mFlairApi.deleteFlairTemplate(subreddit, templateId);
	}

	@Override
	public void flair(String subreddit, String name, String link, String text,
			String cssClass) {
		mFlairApi.flair(subreddit, name, link, text, cssClass);
	}

	@Override
	public void flairConfig(String subreddit, boolean enabled,
			String flairPosition, boolean flairSelfAssignEnabled,
			String linkFlairPosition, boolean linkFlairSelfAssignEnabled) {
		mFlairApi.flairConfig(subreddit, enabled, flairPosition,
				flairSelfAssignEnabled, linkFlairPosition,
				linkFlairSelfAssignEnabled);
	}

	@Override
	public void flairCsv(String subreddit, String flairCsv) {
		mFlairApi.flairCsv(subreddit, flairCsv);
	}

	@Override
	public FlairListing getFlairListing(String subreddit, String before,
			String after, int count, int limit, String name) {
		return mFlairApi.getFlairListing(subreddit, before, after, count,
				limit, name);
	}

	@Override
	public void flairTemplate(String subreddit, String cssClass,
			String flairTemplateId, String flairType, String text,
			boolean textEditable) {
		mFlairApi.flairTemplate(subreddit, cssClass, flairTemplateId,
				flairType, text, textEditable);
	}

	@Override
	public void selectFlair(String subreddit, String flairTemplateId,
			String link, String name, String text) {
		mFlairApi.selectFlair(subreddit, flairTemplateId, link, name, text);
	}

	@Override
	public void setFlairEnabled(String subreddit, boolean enabled) {
		mFlairApi.setFlairEnabled(subreddit, enabled);
	}

	@Override
	public CommentResponse comment(String parentId, String text) throws RedditEngineException {
		return mLinkCommentApi.comment(parentId, text);
	}

	@Override
	public void delete(String thingId) throws RedditEngineException {
		mLinkCommentApi.delete(thingId);
	}

	@Override
	public EditResponse edit(String thingId, String text) throws RedditEngineException {
		return mLinkCommentApi.edit(thingId, text);
	}

	@Override
	public void hide(String thingId) throws RedditEngineException {
		mLinkCommentApi.hide(thingId);
	}

	@Override
	public void unhide(String thingId) throws RedditEngineException {
		mLinkCommentApi.unhide(thingId);
	}

	@Override
	public void info(String url, String thingId, int limit) throws RedditEngineException {
		mLinkCommentApi.info(url, thingId, limit);
	}

	@Override
	public void markNSFW(String thingId) throws RedditEngineException {
		mLinkCommentApi.markNSFW(thingId);
	}

	@Override
	public void unmarkNSFW(String thingId) throws RedditEngineException {
		mLinkCommentApi.unmarkNSFW(thingId);
	}

	@Override
	public CommentListing moreChildren(String linkId, String children,
			String moreId, String sort) {
		return mLinkCommentApi.moreChildren(linkId, children, moreId, sort);
	}

	@Override
	public void report(String thingId) throws RedditEngineException {
		mLinkCommentApi.report(thingId);
	}

	@Override
	public void save(String thingId, String category) throws RedditEngineException {
		mLinkCommentApi.save(thingId, category);
	}

	@Override
	public void unsave(String thingId) throws RedditEngineException {
		mLinkCommentApi.unsave(thingId);
	}

	@Override
	public void setContestMode(String thingId, boolean state) throws RedditEngineException {
		mLinkCommentApi.setContestMode(thingId, state);
	}

	@Override
	public void setSticky(String thingId, boolean state) throws RedditEngineException {
		mLinkCommentApi.setSticky(thingId, state);
	}

	@Override
	public SubmitResponse submit(String subreddit, String title, String kind, String url,
			String text, String captcha, String captchaIden, boolean resubmit,
			boolean save, boolean sendReplies) throws RedditEngineException {
		return mLinkCommentApi.submit(subreddit, title, kind, url, text, captcha,
				captchaIden, resubmit, save, sendReplies);
	}

	@Override
	public void vote(String thingId, int dir) throws RedditEngineException {
		mLinkCommentApi.vote(thingId, dir);
	}

	@Override
	public CommentListing getCommentListing(String subreddit, String article,
			String comment, int context, int depth, int limit, String sort)
			throws RedditEngineException {
		return mListingsApi.getCommentListing(subreddit, article, comment,
				context, depth, limit, sort);
	}

	@Override
	public LinkListing getLinkListingByName(String name) throws RedditEngineException {
		return mListingsApi.getLinkListingByName(name);
	}

	@Override
	public LinkListing getLinkListing(String subreddit, String order,
			String topScope, String before, String after, int limit)
			throws RedditEngineException {
		return mListingsApi.getLinkListing(subreddit, order, topScope, before,
				after, limit);
	}

	@Override
	public void block(String thingId) {
		mMessagesApi.block(thingId);
	}

	@Override
	public ComposeResponse compose(String to, String subject, String text, String captcha,
			String captchaIdentity) throws RedditEngineException {
		return mMessagesApi.compose(to, subject, text, captcha, captchaIdentity);
	}

	@Override
	public void readMessage(String thingId) throws RedditEngineException {
		mMessagesApi.readMessage(thingId);
	}

	@Override
	public void unreadMessage(String thingId) throws RedditEngineException {
		mMessagesApi.unreadMessage(thingId);
	}

	@Override
	public MessageListing getMessageListing(String where, boolean mark,
			String mid, String before, String after, int count, int limit,
			String show) throws RedditEngineException {
		return mMessagesApi.getMessageListing(where, mark, mid, before, after,
				count, limit, show);
	}

	@Override
	public ModlogListing getModlogListing(String subreddit, String type,
			String mod, String before, String after, int count, int limit) throws RedditEngineException {
		return mModerationApi.getModlogListing(subreddit, type, mod, before,
				after, count, limit);
	}

	@Override
	public void acceptModeratorInvite(String subreddit) {
		mModerationApi.acceptModeratorInvite(subreddit);
	}

	@Override
	public void approve(String thingId) {
		mModerationApi.approve(thingId);
	}

	@Override
	public void distinguish(String thingId, String how) {
		mModerationApi.distinguish(thingId, how);
	}

	@Override
	public void ignoreReports(String thingId) {
		mModerationApi.ignoreReports(thingId);
	}

	@Override
	public void unignoreReports(String thingId) {
		mModerationApi.unignoreReports(thingId);
	}

	@Override
	public void leaveContributor(String thingId) {
		mModerationApi.leaveContributor(thingId);
	}

	@Override
	public void leaveModerator(String thingId) {
		mModerationApi.leaveModerator(thingId);
	}

	@Override
	public void remove(String thing, boolean spam) {
		mModerationApi.remove(thing, spam);
	}

	@Override
	public StyleSheet getStyleSheet(String subreddit) throws RedditEngineException {
		return mModerationApi.getStyleSheet(subreddit);
	}

	@Override
	public LabeledMulti[] getMyMultis() throws RedditEngineException {
		return mMultiApi.getMyMultis();
	}

	@Override
	public void deleteMulti(String path) throws RedditEngineException {
		mMultiApi.deleteMulti(path);
	}

	@Override
	public LabeledMulti getMulti(String path) throws RedditEngineException {
		return mMultiApi.getMulti(path);
	}

	@Override
	public void createOrEditMulti(String path, String[] subreddits,
			boolean isPublic) throws RedditEngineException {
		mMultiApi.createOrEditMulti(path, subreddits, isPublic);
	}

	@Override
	public void copyMulti(String path, String from, String to) throws RedditEngineException {
		mMultiApi.copyMulti(path, from, to);
	}

	@Override
	public String getMultiDescription(String path) throws RedditEngineException {
		return mMultiApi.getMultiDescription(path);
	}

	@Override
	public void setMultiDescription(String path, String text) throws RedditEngineException {
		mMultiApi.setMultiDescription(path, text);
	}

	@Override
	public void removeSubredditFromMulti(String path, String subreddit) throws RedditEngineException {
		mMultiApi.removeSubredditFromMulti(path, subreddit);
	}

	@Override
	public void addSubredditToMulti(String path, String subreddit) throws RedditEngineException {
		mMultiApi.addSubredditToMulti(path, subreddit);
	}

	@Override
	public String getSubredditInfoInMulti(String path, String subreddit) throws RedditEngineException {
		return mMultiApi.getSubredditInfoInMulti(path, subreddit);
	}

	@Override
	public void renameMulti(String path, String from, String to) throws RedditEngineException {
		mMultiApi.renameMulti(path, from, to);
	}

	@Override
	public LinkListing search(String subreddit, String query,
			boolean restrictToSubreddit, String before, String after,
			int count, int limit, String sort, String syntax, String timeScope, String show) throws RedditEngineException {
		return mSearchApi.search(subreddit, query, restrictToSubreddit, before,
				after, count, limit, sort, syntax, timeScope, show);
	}

	@Override
	public void deleteSubredditHeader(String subreddit) {
		mSubredditsApi.deleteSubredditHeader(subreddit);
	}

	@Override
	public void deleteSubredditImage(String subreddit, String imageName) {
		mSubredditsApi.deleteSubredditImage(subreddit, imageName);
	}

	@Override
	public String[] getSubredditRecomendations(String[] reddits, String[] omit) throws RedditEngineException {
		return mSubredditsApi.getSubredditRecomendations(reddits, omit);
	}

	@Override
	public String[] searchSubreddits(String startswith, boolean withNSFW) throws RedditEngineException {
		return mSubredditsApi.searchSubreddits(startswith, withNSFW);
	}

	@Override
	public void siteAdmin(String name, SubredditSettings settings) {
		mSubredditsApi.siteAdmin(name, settings);
	}

	@Override
	public String getSubredditSubmitText(String subreddit) throws RedditEngineException {
		return mSubredditsApi.getSubredditSubmitText(subreddit);
	}

	@Override
	public String getSubredditStylesheet(String subreddit, String operation,
			String revid, String contest) {
		return mSubredditsApi.getSubredditStylesheet(subreddit, operation,
				revid, contest);
	}

	@Override
	public String[] getSubredditsByTopic(String query) throws RedditEngineException {
		return mSubredditsApi.getSubredditsByTopic(query);
	}

	@Override
	public void subscribeSubreddit(String subreddit) throws RedditEngineException {
		mSubredditsApi.subscribeSubreddit(subreddit);
	}

	@Override
	public void unsubscribeSubreddit(String subreddit) throws RedditEngineException {
		mSubredditsApi.unsubscribeSubreddit(subreddit);
	}

	@Override
	public Subreddit aboutSubreddit(String subreddit) throws RedditEngineException {
		return mSubredditsApi.aboutSubreddit(subreddit);
	}

	@Override
	public SubredditSettings getSubredditSettings(String subreddit) {
		return mSubredditsApi.getSubredditSettings(subreddit);
	}

	@Override
	public SubredditListing getMySubreddits(String where, String before,
			String after, int count, int limit, String show) throws RedditEngineException {
		return mSubredditsApi.getMySubreddits(where, before, after, count,
				limit, show);
	}

	@Override
	public SubredditListing searchSubreddits(String query, String before,
			String after, int count, int limit, String show) throws RedditEngineException {
		return mSubredditsApi.searchSubreddits(query, before, after, count,
				limit, show);
	}

	@Override
	public SubredditListing getSubreddits(String which, String before,
			String after, int count, int limit, String show) throws RedditEngineException {
		return mSubredditsApi.getSubreddits(which, before, after, count,
				limit, show);
	}

	@Override
	public JsonResponse<?> friend(String user, String container, String type,
			String permissions, String note) throws RedditEngineException {
		return mUserApi.friend(user, container, type, permissions, note);
	}

	@Override
	public void unfriend(String user, String thingId, String container,
			String type) throws RedditEngineException {
		mUserApi.unfriend(user, thingId, container, type);
	}

	@Override
	public void setPermissions(String user, String subreddit, String permissions, String type) throws RedditEngineException {
		mUserApi.setPermissions(user, subreddit, permissions, type);
	}

	@Override
	public boolean isUsernameAvailable(String user) throws RedditEngineException {
		return mUserApi.isUsernameAvailable(user);
	}

	@Override
	public User aboutUser(String user) throws RedditEngineException {
		return mUserApi.aboutUser(user);
	}

	@Override
	public UserListing getUserListing(String user, String what) throws RedditEngineException {
		return mUserApi.getUserListing(user, what);
	}

	@Override
	public void addWikiEditor(String subreddit, String page, String user) {
		mWikiApi.addWikiEditor(subreddit, page, user);
	}

	@Override
	public void removeWikiEditor(String subreddit, String page, String user) {
		mWikiApi.removeWikiEditor(subreddit, page, user);
	}

	@Override
	public void editWiki(String subreddit, String page, String prevRevision,
			String reaason) {
		mWikiApi.editWiki(subreddit, page, prevRevision, reaason);
	}

	@Override
	public void hideWiki(String subreddit, String page) {
		mWikiApi.hideWiki(subreddit, page);
	}

	@Override
	public void revertWiki(String subreddit, String page, String revision) {
		mWikiApi.revertWiki(subreddit, page, revision);
	}

	// Separate API's
	private AccountApi mAccountApi;
	private AppsApi mAppsApi;
	private CaptchaApi mCaptchaApi;
	private FlairApi mFlairApi;
	private LinkCommentApi mLinkCommentApi;
	private ListingsApi mListingsApi;
	private MessagesApi mMessagesApi;
	private ModerationApi mModerationApi;
	private MultiApi mMultiApi;
	private SearchApi mSearchApi;
	private SubredditsApi mSubredditsApi;
	private UserApi mUserApi;
	private WikiApi mWikiApi;
}
