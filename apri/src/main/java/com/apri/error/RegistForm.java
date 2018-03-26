package com.apri.error;

import java.io.Serializable;

import javax.validation.constraints.Max;

import com.apri.type.BytesLength;

public class RegistForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
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
