package net.fizzl.redditengine.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
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

import com.google.gson.Gson;

import android.net.http.AndroidHttpClient;
import android.util.Log;

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
		//get.addHeader(new BasicHeader(UrlUtils.X_MODHASH, ???));
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
		//post.addHeader(new BasicHeader(UrlUtils.X_MODHASH, ???));
		post.setEntity(new UrlEncodedFormEntity(params));
		HttpResponse response = mClient.execute(post, mHttpContext);
		StatusLine line = response.getStatusLine();
		if(line.getStatusCode() != HttpStatus.SC_OK) {
			String msg = String.format("Unexpected return code %s", line.getReasonPhrase());
			UnexpectedHttpResponseException ex = new UnexpectedHttpResponseException(msg);
			throw ex;
		}
		InputStream in = response.getEntity().getContent();
		InputStream is = clone(in);
		return is;
	}
	
	private InputStream clone(InputStream in) {
		InputStream retval = in;
		// TODO use tee and pipes and not a byte array
		try {
			byte[] bytes = IOUtils.toByteArray(in);
			retval = new ByteArrayInputStream(bytes);
			in.close();
			InputStream is = new ByteArrayInputStream(bytes);
			StringWriter writer = new StringWriter();
			IOUtils.copy(is, writer, "UTF-8");
			String json = writer.toString();
			is.close();
			Log.d("clone IS:", json);
			HashMap data = new Gson().fromJson(json, HashMap.class);
			String modhash = (String) findKeyRecursion(data, "modhash");
			if (modhash != null) {
				Log.d("clone IS:", "modhash = " + modhash);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return retval;
	}
	
	private Object findKeyRecursion (Map map, Object find) {
		Set entry = map.entrySet();
		Set keys = map.keySet();
		Collection values = map.values();
		boolean found = map.containsKey(find);
		
		if (found) {
			Object get = map.get(find);
			return get;
		}
		
		for (Object value : values) {
			// TODO what about Lists?
			if (value instanceof Map) return findKeyRecursion ((Map) value, find);
		}
		
		return null;
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
}
