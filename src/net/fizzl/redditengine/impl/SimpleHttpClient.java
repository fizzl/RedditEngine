package net.fizzl.redditengine.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.net.http.AndroidHttpClient;

/**
* This class provides simple HTTP GET and POST operations using Apache HttpComponents.
* @see AndroidHttpClient
*/
public class SimpleHttpClient {
	// API
	/**
	 * Calls HTTP GET with the Request-URI. Returns an InputStream of the response entity.
	 * 
	 * @param url		HTTP GET URL
	 * @param params	GET parameters
	 * @return			InputStream
	 * @throws 			ClientProtocolException
	 * @throws 			IOException
	 * @throws 			UnexpectedHttpResponseException
	 */
	public InputStream get(String url, List<NameValuePair> params) throws ClientProtocolException, IOException, UnexpectedHttpResponseException {
		if(params != null) {
			String strp = URLEncodedUtils.format(params, "UTF-8");
			url += "?" + strp;
		}
		HttpGet get = new HttpGet(url);
		HttpResponse response = mClient.execute(get, mHttpContext);
		StatusLine line = response.getStatusLine();
		if(line.getStatusCode() != HttpStatus.SC_OK) {
			String msg = String.format("Unexpected return code %s", line.getReasonPhrase());
			UnexpectedHttpResponseException ex = new UnexpectedHttpResponseException(msg);
			throw ex;
		}
		return response.getEntity().getContent();
	}
	
	/* Response headers to monitor for:
	 * X-Ratelimit-Used: Approximate number of requests used in this period
	 * X-Ratelimit-Remaining: Approximate number of requests left to use
	 * X-Ratelimit-Reset: Approximate number of seconds to end of period
	 * 
	 * TODO: include an X-Modhash custom HTTP header
	 * HttpGet.addHeader(new BasicHeader(X_MODHASH, modhash));
	 */
	
	/**
	 * Calls HTTP POST with the Request-URI. Returns an InputStream of the response entity.
	 * 
	 * @param url		HTTP POST URL
	 * @param params	POST parameters
	 * @return			InputStream
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws UnexpectedHttpResponseException
	 */
	public InputStream post(String url, List<NameValuePair> params) throws ClientProtocolException, IOException, UnexpectedHttpResponseException {
		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(params));
		HttpResponse response = mClient.execute(post, mHttpContext);
		StatusLine line = response.getStatusLine();
		if(line.getStatusCode() != HttpStatus.SC_OK) {
			String msg = String.format("Unexpected return code %s", line.getReasonPhrase());
			UnexpectedHttpResponseException ex = new UnexpectedHttpResponseException(msg);
			throw ex;
		}
		
		return response.getEntity().getContent();
	}
	
	// Singleton
	/**
	 * Private constructor. To get an instance use {@link #getInstance()}
	 * @see AndroidHttpClient
	 */
	private SimpleHttpClient() {
		mClient = AndroidHttpClient.newInstance(USER_AGENT);
		HttpParams params = mClient.getParams();
		HttpClientParams.setRedirecting(params, true);
		mCookieStore = new PersistentCookieStore();
		mHttpContext = new BasicHttpContext();
		mHttpContext.setAttribute(ClientContext.COOKIE_STORE, mCookieStore);
	}

	/**
	 * Returns a (singleton) instance of this class
	 * 
	 * @return	SimpleHttpClient
	 */
	public static SimpleHttpClient getInstance() {
		if(me == null) {
			me = new SimpleHttpClient();
		}
		return me;
	}
	private static SimpleHttpClient me;

	// Local variables
	private HttpClient mClient;
	private HttpContext mHttpContext;
	private CookieStore mCookieStore;
	
	// Constants
	private static final String USER_AGENT = "fizzl.net/RedditEngine 1.0/SimpleHttpClient 1.1/by fizzl";
	private static final String X_MODHASH = "X-Modhash";
}
