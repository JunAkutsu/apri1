package com.apri.message;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.apri.type.BytesLength;
import com.lowagie.text.List;

public class RegistForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@BytesLength(length=15,encoding="MS932")
	private String name;
	@Min(2)
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
