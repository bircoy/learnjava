package com.learnjava.numbers;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Number {

	@Id 
	@GeneratedValue
	private Integer id;
	private Integer value;
	private Date createdAt;

	public Number() {
	}
	
	public Number(Integer value) {
		super();
		this.value = value;
	}
	
	public Number(Integer value, Date createdAt) {
		super();
		this.value = value;
		this.createdAt = createdAt;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Number [id=" + id + ", value=" + value + ", createdAt=" + createdAt + "]";
	}
}
