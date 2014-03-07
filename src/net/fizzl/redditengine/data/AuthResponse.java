package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import org.apache.commons.io.IOUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AuthResponse {
	public static AuthResponse fromInputStream(InputStream is) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromString(writer.toString());
	}

	public static AuthResponse fromString(String str) {
		Gson gson = new GsonBuilder().create();
		AuthResponse ret = gson.fromJson(str, AuthResponse.class);
		return ret;
	}

	public AuthJsonResponse getJson() {
		return json;
	}

	public void setJson(AuthJsonResponse json) {
		this.json = json;
	}

	private AuthJsonResponse json;	
}
