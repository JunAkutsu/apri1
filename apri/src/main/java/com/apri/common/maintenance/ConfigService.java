package com.apri.common.maintenance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.apri.common.exception.ApplicationException;

import ch.qos.logback.classic.Level;

@Service
@Scope("singleton")
public class ConfigService {

    protected final Logger logger = LoggerFactory.getLogger("com.apri");
    
	// SQLのログを出力するFLG
	// true:表示、false：非表示
	private boolean sql_log_hyouji_flg = false;

	public boolean isSql_log_hyouji_flg() {
		return sql_log_hyouji_flg;
	}

	public void setSql_log_hyouji_flg(boolean sql_log_hyouji_flg) {
		this.sql_log_hyouji_flg = sql_log_hyouji_flg;
		ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger)logger;
		if(this.sql_log_hyouji_flg){
			log.setLevel(Level.DEBUG);
		}
		else{
			log.setLevel(Level.INFO);
		}
	}



}
