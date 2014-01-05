package net.fizzl.redditengine.data;

public class Media {
	OEmbed oembed;
	static class OEmbed {
		String provider_url;
		String description;
		String title;
		String url;
		String author_name;
	}
}
