package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;

/**
 * GSON wrapper for compose response
 * 
 * @see ComposeResponseJson
 */
public class ComposeResponse {
	public static ComposeResponse fromInputStream(InputStream is) throws IOException {
		return GsonTemplate.fromInputStream(is, ComposeResponse.class);
	}
	
	private ComposeResponseJson json;
		
	public ComposeResponseJson getJson() {
		return json;
	}

	public void setJson(ComposeResponseJson json) {
		this.json = json;
	}
}
