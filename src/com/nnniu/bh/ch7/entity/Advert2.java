package com.nnniu.bh.ch7.entity;

public class Advert2 {

	private long id = -1;
	private String title;
	private String content;
	private Picture picture;
	
	Advert2() {
		
	}
	
	public Advert2(String title, String content, Picture picture) {
		this.title = title;
		this.content = content;
		this.picture = picture;
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

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	
}
