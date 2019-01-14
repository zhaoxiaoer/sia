package com.nnniu.bh.ch7.entity;

public class Picture {
	
	private long id = -1;
	private String caption;
	private String filename;

	Picture() {
		
	}
	
	public Picture(String caption, String filename) {
		this.caption = caption;
		this.filename = filename;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
