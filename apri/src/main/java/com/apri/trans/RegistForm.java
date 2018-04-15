package com.apri.trans;

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
	
	private Long tantousya_renban;
	private String tantousya_id;
	private String simei;
	
	public Long getTantousya_renban() {
		return tantousya_renban;
	}
	public void setTantousya_renban(Long tantousya_renban) {
		this.tantousya_renban = tantousya_renban;
	}
	public String getTantousya_id() {
		return tantousya_id;
	}
	public void setTantousya_id(String tantousya_id) {
		this.tantousya_id = tantousya_id;
	}
	public String getSimei() {
		return simei;
	}
	public void setSimei(String simei) {
		this.simei = simei;
	}
	
	
}
