package com.apri.csv3;

import com.orangesignal.csv.annotation.CsvColumn;
import com.orangesignal.csv.annotation.CsvEntity;

@CsvEntity
public class CSVContent {
	
	@CsvColumn(name = "ID")
	private int id;
	
	@CsvColumn(name = "名前")
	private String name;
	
	@CsvColumn(name = "年齢")
	private int age;
	
	@CsvColumn(name = "性別")
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
