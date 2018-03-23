package com.apri.csv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"ID", "名前", "年齢"})
public class CSVContent {
	@JsonProperty("ID")
	private int id;
	
	@JsonProperty("名前")
	private String name;
	
	@JsonProperty("年齢")
	private int age;
	
	@JsonProperty("性別")
	private String sex;
	
    public CSVContent(int id, String name, int age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
