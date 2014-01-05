package net.fizzl.redditengine.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.fizzl.redditengine.RedditApi;
import net.fizzl.redditengine.UrlUtils;
import net.fizzl.redditengine.data.CommentListing;
import net.fizzl.redditengine.data.FlairListing;
import net.fizzl.redditengine.data.LinkListing;
import net.fizzl.redditengine.data.Me;
import net.fizzl.redditengine.data.MessageListing;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import android.content.Context;

public class DefaultRedditApi implements RedditApi {

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
	public void clearSessions(String passwd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String user, String passwd, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void login(String user, String passwd, boolean remember) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Me me() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(String user, String passwd1, String passwd2,
			boolean remember, String email, String captcha, String captcha_iden) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(String passwd, String email, String newpass1,
			String newpass2, boolean verify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDeveloper(String appId, String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteApp(String appId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeDeveloper(String appId, String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revokeApp(String appId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAppIcon(String appId, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateApp(String appId, String appName, String aboutUrl,
			String iconUrl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean needCaptcha() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String newCaptcha() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String captchaImageUrl(String captchaIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearFlairTemplates(String subreddit, String flair_type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFlair(String subreddit, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFlairTemplate(String subreddit, String templateId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flair(String subreddit, String name, String link, String text,
			String cssClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flairConfig(String subreddit, boolean enabled,
			String flairPosition, boolean flairSelfAssignEnabled,
			String linkFlairPosition, boolean linkFlairSelfAssignEnabled) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flairCsv(String subreddit, String flairCsv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FlairListing getFlairListing(String subreddit, String before,
			String after, int count, int limit, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flairTemplate(String subreddit, String cssClass,
			String flairTemplateId, String flairType, String text,
			boolean textEditable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectFlair(String subreddit, String flairTemplateId,
			String link, String name, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFlairEnabled(String subreddit, boolean enabled) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comment(String parentId, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(String thingId, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unhide(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String url, String thingId, int limit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markNSFW(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unmarkNSFW(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CommentListing moreChildren(String linkId, String children,
			String moreId, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void report(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unsave(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContestMode(String thingId, boolean state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSticky(String thingId, boolean state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submit(String subreddit, String title, String kind, String url,
			String text, String captcha, String captchaIden, boolean resubmit,
			boolean save, boolean sendReplies) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vote(String thingId, int dir) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public CommentListing getCommentListing(String subreddit, String article, 
			String comment, int context, int depth, int limit, String sort) throws ClientProtocolException, IOException, JSONException {
		StringBuilder path = new StringBuilder();
		path.append("/r/");
		path.append(subreddit);
		path.append("/comments/");
		path.append(article);
		String url = UrlUtils.getUrl(path.toString());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(comment != null) {
			params.add(new BasicNameValuePair("comment", comment));
		}
		if(context > 0) {
			params.add(new BasicNameValuePair("context", Integer.toString(context)));
		}
		if(depth > 0) {
			params.add(new BasicNameValuePair("depth", Integer.toString(depth)));
		}
		if(limit > 0) {
			params.add(new BasicNameValuePair("limit", Integer.toString(limit)));
		}
		if(sort != null) {
			params.add(new BasicNameValuePair("sort", sort));
		}
		
		SimpleHttpClient client = SimpleHttpClient.getInstance();
		InputStream is = client.get(url, params);
		CommentListing ret = CommentListing.fromInputStream(is);
		is.close();
		return ret;
	}
	
	@Override
	public LinkListing getLinkListingByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkListing getLinkListing(String subreddit, String order,
			String topScope, String before, String after, int limit)
			throws ClientProtocolException, IOException {
		StringBuilder path = new StringBuilder();
		path.append("/r/");
		path.append(subreddit);
		
		// hot/new/etc...
		if(order != null) {
			path.append("/");
			path.append(order);
		}

		String url = UrlUtils.getUrl(path.toString());

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(after != null) {
			params.add(new BasicNameValuePair("after", after));
		}
		else if(before != null) {
			params.add(new BasicNameValuePair("before", before));
		}
		
		if(limit > 0) {
			params.add(new BasicNameValuePair("limit", Integer.toString(limit)));
		}
		
		SimpleHttpClient client = SimpleHttpClient.getInstance();
		InputStream is = client.get(url, params);
		LinkListing ret = LinkListing.fromInputStream(is);
		is.close();
		return ret;
	}

	@Override
	public void block(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void compose(String to, String subject, String text, String captcha,
			String captchaIdentity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readMessage(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unreadMessage(String thingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MessageListing getMessageListing(String where, boolean mark,
			String mid, String before, String after, int count, int limit,
			String show) {
		// TODO Auto-generated method stub
		return null;
	}
}
