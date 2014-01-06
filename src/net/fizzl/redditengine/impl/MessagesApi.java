package net.fizzl.redditengine.impl;

import net.fizzl.redditengine.data.MessageListing;

public class MessagesApi extends BaseApi {
	public void block(String thingId){
		throw new UnimplementedException();
	}

	public void compose(String to, String subject, String text, String captcha, String captchaIdentity){
		throw new UnimplementedException();
	}

	public void readMessage(String thingId){
		throw new UnimplementedException();
	}

	public void unreadMessage(String thingId){
		throw new UnimplementedException();
	}

	public MessageListing getMessageListing(String where, boolean mark, String mid, String before, 
			String after, int count, int limit, String show){
		throw new UnimplementedException();
	}

}
