package com.apri.form;

import java.io.Serializable;

import javax.validation.constraints.Max;

import com.apri.type.BytesLength;

public class RegistForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@BytesLength(length=15,encoding="MS932")
	private String name;
	@Max(10)
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	
}
