package net.fizzl.redditengine.impl;

import java.io.Serializable;
import java.util.Date;

import org.apache.http.cookie.Cookie;

/**
 * TODO later version of BasicClientCookie implements Serializable, use that
 */
public class SerializableCookie implements Cookie, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean isSecure() {
		return secure;
	}

	@Override
	public boolean isExpired(Date date) {
		boolean expired = (expiryDate != null && date.after(expiryDate));
		return expired;
	}
	
	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public String getCommentURL() {
		return commentURL;
	}

	@Override
	public String getDomain() {
		return domain;
	}

	@Override
	public Date getExpiryDate() {
		return expiryDate;
	}

	@Override
	public int[] getPorts() {
		return ports;
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public boolean isPersistent() {
		boolean persistent = (null != expiryDate);
		return persistent;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getPath() {
		return path;
	}

	private String comment;
	private String commentURL;
	private String domain;		
	private Date expiryDate;
	private int[] ports;
	private int version;
	private String name;
	private String value;
	private String path;
	private boolean secure;

	public SerializableCookie(Cookie cookie) {
		this.comment = cookie.getComment();
		this.commentURL = cookie.getCommentURL();
		this.domain = cookie.getDomain();
		this.expiryDate = cookie.getExpiryDate();
		this.name = cookie.getName();
		this.path = cookie.getPath();
		this.ports = cookie.getPorts();
		this.value = cookie.getValue();
		this.version = cookie.getVersion();
		this.secure = cookie.isSecure();
	}		
}