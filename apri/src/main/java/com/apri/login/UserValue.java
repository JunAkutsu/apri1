package com.apri.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserValue {

	private Long tantousya_renban;
	private String tantousya_id;
	private String password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSimei() {
		return simei;
	}
	public void setSimei(String simei) {
		this.simei = simei;
	}
	

}
