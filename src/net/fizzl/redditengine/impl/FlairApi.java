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

import net.fizzl.redditengine.data.FlairListing;

public class FlairApi extends BaseApi {
	public void clearFlairTemplates(String subreddit, String flair_type){
		throw new UnimplementedException();
	}

	public void deleteFlair(String subreddit, String name){
		throw new UnimplementedException();
	}

	public void deleteFlairTemplate(String subreddit, String templateId){
		throw new UnimplementedException();
	}

	public void flair(String subreddit, String name, String link, String text, String cssClass){
		throw new UnimplementedException();
	}

	public void flairConfig(String subreddit, boolean enabled, String flairPosition, 
			boolean flairSelfAssignEnabled, String linkFlairPosition, boolean linkFlairSelfAssignEnabled){
		throw new UnimplementedException();
	}

	public void flairCsv(String subreddit, String flairCsv){
		throw new UnimplementedException();
	}

	public FlairListing getFlairListing(String subreddit, String before, String after, int count, int limit, String name){
		throw new UnimplementedException();
	}

	public void flairTemplate(String subreddit, String cssClass, String flairTemplateId, String flairType, String text, boolean textEditable){
		throw new UnimplementedException();
	}

	public void selectFlair(String subreddit, String flairTemplateId, String link, String name, String text){
		throw new UnimplementedException();
	}

	public void setFlairEnabled(String subreddit, boolean enabled){
		throw new UnimplementedException();
	}
}
