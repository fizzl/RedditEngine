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

public class UserData {
	String id;
	String name;
	boolean has_mail;
	boolean has_verified_email;
	boolean is_friend;
	double created;
	double created_utc;
	String modhash;
	int link_karma;
	int comment_karma;
	boolean over_18;
	boolean is_gold;
	boolean is_mod;
	boolean has_mod_mail;
	
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
	public boolean isHas_mail() {
		return has_mail;
	}
	public void setHas_mail(boolean has_mail) {
		this.has_mail = has_mail;
	}
	public boolean isHas_verified_email() {
		return has_verified_email;
	}
	public void setHas_verified_email(boolean has_verified_email) {
		this.has_verified_email = has_verified_email;
	}
	public boolean isIs_friend() {
		return is_friend;
	}
	public void setIs_friend(boolean is_friend) {
		this.is_friend = is_friend;
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
	public String getModhash() {
		return modhash;
	}
	public void setModhash(String modhash) {
		this.modhash = modhash;
	}
	public int getLink_karma() {
		return link_karma;
	}
	public void setLink_karma(int link_karma) {
		this.link_karma = link_karma;
	}
	public int getComment_karma() {
		return comment_karma;
	}
	public void setComment_karma(int comment_karma) {
		this.comment_karma = comment_karma;
	}
	public boolean isOver_18() {
		return over_18;
	}
	public void setOver_18(boolean over_18) {
		this.over_18 = over_18;
	}
	public boolean isIs_gold() {
		return is_gold;
	}
	public void setIs_gold(boolean is_gold) {
		this.is_gold = is_gold;
	}
	public boolean isIs_mod() {
		return is_mod;
	}
	public void setIs_mod(boolean is_mod) {
		this.is_mod = is_mod;
	}
	public boolean isHas_mod_mail() {
		return has_mod_mail;
	}
	public void setHas_mod_mail(boolean has_mod_mail) {
		this.has_mod_mail = has_mod_mail;
	}
}
