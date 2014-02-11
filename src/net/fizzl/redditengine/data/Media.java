package net.fizzl.redditengine.data;

public class Media {
	OEmbed oembed;
	
	public OEmbed getOembed() {
		return oembed;
	}

	public void setOembed(OEmbed oembed) {
		this.oembed = oembed;
	}

	static class OEmbed {
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
