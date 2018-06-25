package com.apri.xlsx_pdf;

import java.io.Serializable;

import org.apache.poi.ss.usermodel.Workbook;

public class XLSXTemplateHolder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String file_name = "data.xlsx";
	private Workbook wb;
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public Workbook getWb() {
		return wb;
	}
	public void setWb(Workbook wb) {
		this.wb = wb;
	}
	
	
}
