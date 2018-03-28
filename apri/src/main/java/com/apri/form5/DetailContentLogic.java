package com.apri.form5;

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
	// messageのKeyを{}で括るとpropertiesファイルのメッセージと置換してくれる。
//	@Min(value=10,message="値段は{value}より大きい値を設定してください。")
	@Min(value=10,message="{javax.validation.constraints.Min.message2}")
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
