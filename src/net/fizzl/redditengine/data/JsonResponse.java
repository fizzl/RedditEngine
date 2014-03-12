package net.fizzl.redditengine.data;

public class JsonResponse<T> {
	public JsonResponseData<T> getJson() {
		return json;
	}

	public void setJson(JsonResponseData<T> json) {
		this.json = json;
	}

	private JsonResponseData<T> json;	
}

