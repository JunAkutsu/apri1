package com.apri.select2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.apri.type.BytesLength;

public class SelectForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long tantousya_renban;

	public Long getTantousya_renban() {
		return tantousya_renban;
	}

	public void setTantousya_renban(Long tantousya_renban) {
		this.tantousya_renban = tantousya_renban;
	}
	
}
