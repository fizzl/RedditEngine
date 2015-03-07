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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import net.fizzl.redditengine.data.LinkListing;

public class SearchApi extends BaseApi {
	public LinkListing search(String subreddit, String query, boolean restrictToSubreddit, String before, String after, int count, 
			int limit, String sort, String syntax, String timeScope, String show) throws RedditEngineException {
		//GET [/r/subreddit]/search[ .json | .xml ]
		// TODO make int types optional as well?
		StringBuilder sb = new StringBuilder();
		if (subreddit != null) {
			sb.append("/r/");
			sb.append(subreddit);
		}
		sb.append("/search");
		String url = UrlUtils.getGetUrl(sb.toString());
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("q", query));
		params.add(new BasicNameValuePair("restrict_sr", String.valueOf(restrictToSubreddit)));
		if (after != null) {
			params.add(new BasicNameValuePair("after", after));
		}
		if (before != null) {
			params.add(new BasicNameValuePair("before", before));
		}
		params.add(new BasicNameValuePair("count", String.valueOf(count)));
		params.add(new BasicNameValuePair("limit", String.valueOf(limit)));
		if (sort != null) {
			params.add(new BasicNameValuePair("sort", sort));
		}
		if (syntax != null) {
			params.add(new BasicNameValuePair("syntax", syntax));
		}
		if (timeScope != null) {
			params.add(new BasicNameValuePair("t", timeScope));
		}
		if (show != null) {
			params.add(new BasicNameValuePair("show", show));
		}

		LinkListing retval = new LinkListing();
		try {
			SimpleHttpClient client = SimpleHttpClient.getInstance();
			InputStream is = client.get(url, params);
			retval = LinkListing.fromInputStream(is);
			is.close();
		} catch (ClientProtocolException e) {
			throw new RedditEngineException(e);
		} catch (IOException e) {
			throw new RedditEngineException(e);
		} catch (UnexpectedHttpResponseException e) {
			throw new RedditEngineException(e);
		}
		
		return retval;
	}
}
