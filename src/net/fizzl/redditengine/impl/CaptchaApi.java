package net.fizzl.redditengine.impl;

public class CaptchaApi extends BaseApi {
	public boolean needCaptcha(){
		throw new UnimplementedException();
	}

	public String newCaptcha(){
		throw new UnimplementedException();
	}

	public String captchaImageUrl(String captchaIdentity){
		throw new UnimplementedException();
	}

}
