package net.fizzl.redditengine.data;

import java.io.IOException;
import java.io.InputStream;

/**
 * Wrapper class for reddit api login method response
 * 
 * @see AuthJsonResponseData
 * @see JsonResponse
 */
public class AuthResponse extends JsonResponse<AuthJsonResponseData> {
	public static AuthResponse fromInputStream(InputStream is) throws IOException {
		return GsonTemplate.fromInputStream(is, AuthResponse.class);
	}
}
