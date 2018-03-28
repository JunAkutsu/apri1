package com.apri.form4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.apri.type.BytesLength;

public class RegistForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int age;
	
	private List<DetailContentLogic> contents = new ArrayList<>();

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

	public List<DetailContentLogic> getContents() {
		return contents;
	}

	public void setContents(List<DetailContentLogic> contents) {
		this.contents = contents;
	}

	public void addContents(DetailContentLogic contents) {
		this.contents.add(contents);
	}
	
}
