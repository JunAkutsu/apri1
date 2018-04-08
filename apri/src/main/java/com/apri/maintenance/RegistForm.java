package com.apri.maintenance;

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
	
	private boolean sql_log_hyouji_flg;

	public boolean isSql_log_hyouji_flg() {
		return sql_log_hyouji_flg;
	}

	public void setSql_log_hyouji_flg(boolean sql_log_hyouji_flg) {
		this.sql_log_hyouji_flg = sql_log_hyouji_flg;
	}
	
}
