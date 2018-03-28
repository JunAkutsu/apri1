package com.apri.form4;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.apri.type.BytesLength;
import com.lowagie.text.List;

public class DetailContentLogic implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String itemName;
	private int price;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
