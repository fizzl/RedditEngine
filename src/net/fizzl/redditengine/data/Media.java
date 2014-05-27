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
