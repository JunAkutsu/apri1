package com.apri.csv4;

import com.orangesignal.csv.annotation.CsvColumn;
import com.orangesignal.csv.annotation.CsvEntity;

@CsvEntity
public class TantousyaDomain {

	@CsvColumn(name = "担当者連番")
	private Long tantousya_renban;
	
	@CsvColumn(name = "担当者ID")
	private String tantousya_id;
	
	@CsvColumn(name = "氏名")
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
