package com.apri.iframe2;

import java.util.List;

public class GyoumuDomain {

	private int no;
	private String gyoumu_meisyou;
	private List<MenuDomain> menu_list;
	
	public List<MenuDomain> getMenu_list() {
		return menu_list;
	}
	public void setMenu_list(List<MenuDomain> menu_list) {
		this.menu_list = menu_list;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getGyoumu_meisyou() {
		return gyoumu_meisyou;
	}
	public void setGyoumu_meisyou(String gyoumu_meisyou) {
		this.gyoumu_meisyou = gyoumu_meisyou;
	}
}
