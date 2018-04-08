package com.apri.common.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

import org.springframework.beans.factory.annotation.Autowired;

import com.apri.common.maintenance.ConfigService;
import ch.qos.logback.classic.Level;

public class SQLLoggingFilter extends Filter<ILoggingEvent> {
	
    @Autowired
	ConfigService configService;
	
    Level level;

	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (event.getMessage().contains("Preparing") || event.getMessage().contains("Parameters")) {
			if(configService.isSql_log_hyouji_flg()){
				return FilterReply.NEUTRAL;
			}
			else{
				return FilterReply.DENY;
			}
		}
		else{
			return FilterReply.NEUTRAL;
		}
	}
	

    public void setLevel(String level) {
        this.level = Level.toLevel(level);
    }

    public void start() {
        if (this.level != null) {
            super.start();
        }
    }
}
