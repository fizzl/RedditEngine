package net.fizzl.redditengine.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
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
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicHeader;
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
		addModhash(get);
		HttpResponse response = mClient.execute(get, mHttpContext);
		
		Header contentType = response.getFirstHeader("Content-Type");
		Log.d("Received(GET) ", contentType.getName() + ": " + contentType.getValue());
		checkHeaders(response);
		
		checkStatusline(response.getStatusLine());
		
		InputStream is = checkContent(response);
		return is;
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
		addModhash(post);
		post.setEntity(new UrlEncodedFormEntity(params));
		HttpResponse response = mClient.execute(post, mHttpContext);
		
		Header contentType = response.getFirstHeader("Content-Type");
		Log.d("Received(POST) ", contentType.getName() + ": " + contentType.getValue());
		checkHeaders(response);
		
		checkStatusline(response.getStatusLine());

		InputStream is = checkContent(response);
		return is;
	}
	
	/**
	 * If response is "application/json", try find a modhash from the contents and return a clone of the InputStream.
	 * Otherwise return the InputStream.
	 * 
	 * @param response	HTTP response
	 * @return InputStream of HTTP response
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private InputStream checkContent(HttpResponse response) throws IllegalStateException, IOException {
		InputStream content = response.getEntity().getContent();
		InputStream ret = null;
		Header contentType = response.getFirstHeader("Content-Type");
		if (contentType != null && contentType.getValue().contains(APPLICATION_JSON)) {
			ret = clone(content);
		} else {
			ret = content;
		}
		
		return ret;
	}
	
	/**
	 * Add modhash to outgoing headers if it is available
	 * @param http	HTTP request
	 */
	void addModhash(HttpRequestBase http) {
		if (lastModhash != null && !lastModhash.isEmpty()) {
			http.addHeader(new BasicHeader(UrlUtils.X_MODHASH, lastModhash));
			Log.d(getClass().getName(), "adding modhash " + lastModhash + " to outgoing headers");
		}		
		Log.d(http.getMethod(), http.getURI().toString());
	}
	
	/**
	 * Check HTTP status code
	 * @param line
	 * @throws UnexpectedHttpResponseException
	 */
	void checkStatusline(StatusLine line) throws UnexpectedHttpResponseException {
		if(line.getStatusCode() != HttpStatus.SC_OK) {
			String msg = String.format("Unexpected return code %s", line.getReasonPhrase());
			UnexpectedHttpResponseException ex = new UnexpectedHttpResponseException(msg);
			throw ex;
		}		
	}
	
	/**
	 * modhash probably isn't in the HTTP response headers, but check anyway
	 * TODO delete this if modhash doesn't exist in headers
	 * @throws UnexpectedHttpResponseException 
	 */
	private void checkHeaders(HttpResponse response) throws UnexpectedHttpResponseException {
		Header[] headers = response.getAllHeaders();
		StringBuilder builder = new StringBuilder();
		for (Header header : headers) {
			builder.append(header.toString());
			builder.append(" ");
		}
		String string = builder.toString();
		if (string.contains("modhash")) {
			Log.e(getClass().getCanonicalName(), "modhash found in " + string);
			throw new UnexpectedHttpResponseException(string);  // to notice this among debug logging
		}
	}
	
	/**
	 * clone inputstream to be able to look for a modhash
	 * TODO use tee and pipes and not a byte array
	 */
	private InputStream clone(InputStream in) {
		InputStream retval = in;  // as a default if an exception is thrown, use the original inputstream
		try {
			byte[] bytes = IOUtils.toByteArray(in);  // consume the original inputstream
			retval = new ByteArrayInputStream(bytes);  // return an inputstream based on copied bytes
			in.close();  // original inputstream is consumed (can't reset, unmarkable) so close it
			InputStream is = new ByteArrayInputStream(bytes);  // create a temporary inputstream from saved bytes
			StringWriter writer = new StringWriter();
			IOUtils.copy(is, writer, "UTF-8");
			String json = writer.toString();
			is.close();  // got a copy of the inputstream as string, close it
			is = null;
			//Log.d("clone IS:", json);
			Object data = new Gson().fromJson(json, Object.class);  // create a POJO data hierarchy from JSON
			String modhash = (String) findKeyRecursion(data, "modhash");  // look for modhash key, find its value
			if (modhash != null) {
				lastModhash = modhash;
				Log.d("cloned inputstream found a modhash: ", modhash);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retval;
	}
	
	private String lastModhash;  // last known modhash
	
	/**
	 * Look for key that matches <tt>find</tt> in data structure recursively and returns its value if found
	 * 
	 * @param object	data hierarchy to go through
	 * @param find		look for this key
	 * @return			value of find (if found, otherwise null)
	 */
	private Object findKeyRecursion (Object object, Object find) {
		Object clazz = object.getClass();
		//Log.d(clazz.toString(), object.toString().substring(0, 255));		
		Collection<?> values = new ArrayList();
		
		if (object instanceof Map) {
			Map<String, ?> map = (Map<String, ?>)object;
			values = map.values();
			//Set<?> entry = map.entrySet();
			//Set<String> keys = map.keySet();
			boolean found = map.containsKey(find);
			if (found) {
				Object get = map.get(find);
				return get;
			}			
		}
		else if (object instanceof List) {
			values = (List<?>)object;
		}
		else {
			Log.d(getClass().getName(), "findKeyRecursion got class " + clazz);
		}

		for (Object value : values) {
			if (value instanceof Map || value instanceof List) {
				return findKeyRecursion (value, find);
			}
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
	private static final String APPLICATION_JSON = "application/json";
}
