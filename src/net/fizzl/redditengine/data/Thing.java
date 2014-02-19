package net.fizzl.redditengine.data;

/**
 * reddit base class
 * 
 * @param <T>	type of data contained in Thing
 */
public class Thing<T> {
	String id;
	String name;
	String kind;
	T data;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
