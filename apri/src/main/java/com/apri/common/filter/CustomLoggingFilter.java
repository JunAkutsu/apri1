package com.apri.common.filter;

import javax.inject.Named;

import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;

import com.apri.common.maintenance.ConfigService;

import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@Named("customLoggingFilter")
public class CustomLoggingFilter extends TurboFilter {

    @Autowired
    private ConfigService configService;

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
    	if(configService == null){
   	        return FilterReply.ACCEPT;
    	}
   		else{
   			// SQLを出力する/出力しないの制御
   			if(format != null){
   	    		if (format.contains("Preparing") || format.contains("Parameters")) {
   	        		if(configService.isSql_log_hyouji_flg()){
   	           	        return FilterReply.ACCEPT;
   	        		}
   	        		else{
   	           	        return FilterReply.DENY;
   	        		}
   	    		}
   			}
	        return FilterReply.ACCEPT;
   		}
    }
}
