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
