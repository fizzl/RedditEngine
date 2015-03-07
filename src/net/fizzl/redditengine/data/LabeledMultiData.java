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

public class LabeledMultiData {
	boolean can_edit;
	String name;
	double created;  // use EditedType if applicable
	double created_utc;
	String visibility;  // on of 'public' or 'private'
	String path;
	NameWrapper[] subreddits;
	
	class NameWrapper {
		String name;
	}

	public boolean isCan_edit() {
		return can_edit;
	}

	public void setCan_edit(boolean can_edit) {
		this.can_edit = can_edit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCreated() {
		return created;
	}

	public void setCreated(double created) {
		this.created = created;
	}

	public double getCreated_utc() {
		return created_utc;
	}

	public void setCreated_utc(double created_utc) {
		this.created_utc = created_utc;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public NameWrapper[] getSubreddits() {
		return subreddits;
	}

	public void setSubreddits(NameWrapper[] subreddits) {
		this.subreddits = subreddits;
	}
}
