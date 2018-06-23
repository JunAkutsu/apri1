package com.apri.xlsx2;

import java.io.Serializable;
import java.util.Map;

public class XLSXTemplateReportData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Map header;
	private Map detailList;
	private int numOfDetailList=0;
	
	public Map getHeader() {
		return header;
	}
	public void setHeader(Map header) {
		this.header = header;
	}
	public Map getDetailList() {
		return detailList;
	}
	public void setDetailList(Map detailList) {
		this.detailList = detailList;
	}
	public int getNumOfDetailList() {
		return numOfDetailList;
	}
	public void setNumOfDetailList(int numOfDetailList) {
		this.numOfDetailList = numOfDetailList;
	}
	

}
