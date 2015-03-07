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
