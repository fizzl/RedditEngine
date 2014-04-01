package net.fizzl.redditengine.data;

/**
 * Class for generic responses that have a json field
 * 
 * @param <T>	type of data in the jsons data field (if any)
 */
public class JsonResponse<T> {
	// TODO should use JsonResponseData as return type but a template class instead
	public JsonResponseData<T> getJson() {
		return json;
	}

	public void setJson(JsonResponseData<T> json) {
		this.json = json;
	}

	private JsonResponseData<T> json;	
}
