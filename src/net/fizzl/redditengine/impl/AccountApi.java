package net.fizzl.redditengine.impl;

import net.fizzl.redditengine.data.User;

public class AccountApi extends BaseApi {
	public void clearSessions(String passwd) {
		throw new UnimplementedException();
	}
	public void deleteUser(String user, String passwd, String message)  {
		throw new UnimplementedException();
	}

	public void login(String user, String passwd, boolean remember)  {
		throw new UnimplementedException();
	}

	public User me()  {
		throw new UnimplementedException();
	}

	public void register(String user, String passwd1, String passwd2, boolean remember, String email, String captcha, String captcha_iden)  {
		throw new UnimplementedException();
	}

	public void updateUser(String passwd, String email, String newpass1, String newpass2, boolean verify)  {
		throw new UnimplementedException();
	}
}
