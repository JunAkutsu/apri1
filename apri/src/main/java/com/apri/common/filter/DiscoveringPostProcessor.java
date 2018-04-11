package com.apri.common.filter;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.filter.Filter;

@Component
public class DiscoveringPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof CustomLoggingFilter){
            Map<String, TurboFilter> filterBeans = applicationContext.getBeansOfType(TurboFilter.class);
            for (TurboFilter filter : filterBeans.values()) {
                LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                for(int i = 0;i<loggerContext.getTurboFilterList().size();i++){
                    TurboFilter data =(TurboFilter)loggerContext.getTurboFilterList().get(i);
                	if(data.getClass().getName().equals(CustomLoggingFilter.class.getName())){
                		filter.setContext(data.getContext());
                		loggerContext.getTurboFilterList().remove(i);
                        loggerContext.getTurboFilterList().add(i,filter);
                	}
                }
            }
        }
        return bean;

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}