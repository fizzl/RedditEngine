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
 package net.fizzl.redditengine.data;

import java.io.Serializable;

public class Media implements Serializable {
	private static final long serialVersionUID = -1755908534760519352L;
	OEmbed oembed;
	
	public OEmbed getOembed() {
		return oembed;
	}

	public void setOembed(OEmbed oembed) {
		this.oembed = oembed;
	}

	static class OEmbed implements Serializable {
		private static final long serialVersionUID = -1842619655794448130L;
		String provider_url;
		String description;
		String title;
		String url;
		String author_name;
		public String getProvider_url() {
			return provider_url;
		}
		public void setProvider_url(String provider_url) {
			this.provider_url = provider_url;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getAuthor_name() {
			return author_name;
		}
		public void setAuthor_name(String author_name) {
			this.author_name = author_name;
		}
		
	}
}
