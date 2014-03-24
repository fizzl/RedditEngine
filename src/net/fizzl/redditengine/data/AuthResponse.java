package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

/**
 * Wrapper class for reddit api login method response
 * 
 * @see AuthJsonResponseData
 * @see JsonResponse
 */
public class AuthResponse extends JsonResponse<AuthJsonResponseData> {
	public static AuthResponse fromInputStream(InputStream is) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(is,  writer, "UTF-8");
		return fromString(writer.toString());
	}

	public static AuthResponse fromString(String str) {
		Gson gson = new Gson();
		AuthResponse ret = gson.fromJson(str, AuthResponse.class);
		return ret;
	}
}
