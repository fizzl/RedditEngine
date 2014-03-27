package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.fizzl.redditengine.data.GsonTemplate;
import net.fizzl.redditengine.data.JsonResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

public class CaptchaApi extends BaseApi {
	/**
	 * Check whether CAPTCHAs are needed for API methods that define the "captcha" and "iden" parameters
	 * @return {@code true} or {@code false}
	 * @throws RedditEngineException
	 */
	public boolean needCaptcha() throws RedditEngineException{
		String url = UrlUtils.getGetUrl("/api/needs_captcha");
		boolean retval = false;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream in = client.get(url, null);
			retval = GsonTemplate.fromInputStream(in, boolean.class);
			in.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}		
		return retval;
	}

	/**
	 * Returns an {@code iden} of a new CAPTCHA
	 * @return {@code iden}
	 * @throws RedditEngineException
	 */
	public String newCaptcha() throws RedditEngineException{
		StringBuilder path = new StringBuilder();
		path.append(UrlUtils.BASE_URL);
		path.append("/api/new_captcha");
		String url = path.toString();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_type", "json"));
		String retval = null;
		
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream in = client.post(url, params);
			JsonResponse<Map<String,String>> response = GsonTemplate.fromInputStream(in, JsonResponse.class);
			in.close();
			Map<String,String> data = response.getJson().getData();
			if (data != null) {
				retval = data.get("iden");
			}
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
		
		return retval;
	}

	/**
	 * Returns a CAPTCHA image for an {@code iden}
	 * @param captchaIdentity	iden
	 * @return 120x50 image/png as {@code InputStream}
	 * @throws RedditEngineException
	 */
	public InputStream captchaImageUrl(String captchaIdentity) throws RedditEngineException{
		// TODO what is the best representation of a PNG image here? using InputStream for now
		StringBuilder sb = new StringBuilder();
		sb.append(UrlUtils.BASE_URL);
		sb.append(String.format("/captcha/%s", captchaIdentity));
		String url = sb.toString();
		InputStream in;
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			in = client.get(url, null);
			// client reads InputStream and calls in.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}		
		return in;
	}

}
