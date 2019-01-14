package com.nnniu.bh.ch7.entity;

import java.util.HashSet;
import java.util.Set;

public class Advert2Onetomany {

	private long id = -1;
	private String title;
	private String content;
	private Set<Picture> pictures = new HashSet<Picture>();
	
	Advert2Onetomany() {
		
	}
	
	public Advert2Onetomany(String title, String content, Set<Picture> pictures) {
		this.title = title;
		this.content = content;
		this.pictures = pictures;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}
	
}
