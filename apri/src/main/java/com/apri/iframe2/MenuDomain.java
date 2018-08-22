package com.apri.iframe2;

import java.util.List;

public class MenuDomain {

	private String menu_bangou;
	private String gyoumu_meisyou;
	private String meisyou;
	private String url;
	private boolean syori_zumi_flg=false;
	
	
	public boolean isSyori_zumi_flg() {
		return syori_zumi_flg;
	}
	public void setSyori_zumi_flg(boolean syori_zumi_flg) {
		this.syori_zumi_flg = syori_zumi_flg;
	}
	public String getMenu_bangou() {
		return menu_bangou;
	}
	public void setMenu_bangou(String menu_bangou) {
		this.menu_bangou = menu_bangou;
	}
	public String getGyoumu_meisyou() {
		return gyoumu_meisyou;
	}
	public void setGyoumu_meisyou(String gyoumu_meisyou) {
		this.gyoumu_meisyou = gyoumu_meisyou;
	}
	public String getMeisyou() {
		return meisyou;
	}
	public void setMeisyou(String meisyou) {
		this.meisyou = meisyou;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
