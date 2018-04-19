package com.apri.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.apri.type.BytesLength;

public class LoginForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tantousya_id;
	private String password;
	
	public String getTantousya_id() {
		return tantousya_id;
	}
	public void setTantousya_id(String tantousya_id) {
		this.tantousya_id = tantousya_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	
}
