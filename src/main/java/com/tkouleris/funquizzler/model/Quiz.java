package com.tkouleris.funquizzler.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quiz {
	@Id
	@GeneratedValue
	private long id;
	
	private String title;
	
	private Timestamp published_at;

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

	public Timestamp getPublished_at() {
		return published_at;
	}

	public void setPublished_at(Timestamp published_at) {
		this.published_at = published_at;
	}
	
	

}
