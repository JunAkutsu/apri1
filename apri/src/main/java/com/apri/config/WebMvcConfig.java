package com.apri.config;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.apri.common.context.Context;
import com.apri.common.context.RequestContext;
import com.apri.common.filter.LoggingFilter;
import com.apri.common.interceptor.SessionExpireInterceptor;
import com.apri.common.maintenance.ConfigService;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer  {
	
	@Autowired
    private MessageSource messageSource;
	
    /**
     * Interceptorを登録する
     */
    @Bean
    public SessionExpireInterceptor sessionExpireInterceptor() {
    	return new SessionExpireInterceptor();
    }
    
    @Bean
    public MappedInterceptor interceptor() {
    	return new MappedInterceptor(new String[]{"/**"}, sessionExpireInterceptor());
    }
	   
    /**
     * Filterを登録する
     * @return FilterRegistrationBean
     */
    @Bean
	public FilterRegistrationBean commonFilter1() {		
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new LoggingFilter());
		bean.setOrder(1);
		return bean;
	}

    
    /**
     * HttpServletRequestを利用したContext実装を利用する（AOPでのロギングで利用する）
     * @param request HttpServletRequest
     * @return Context
     */
    @Bean
    public Context context(HttpServletRequest request) {
        return new RequestContext(request);
    }
    
    /**
     * LocalValidatorFactoryBeanのsetValidationMessageSourceで
     * バリデーションメッセージをValidationMessages.propertiesからSpringの
     * MessageSource(messages.properties)に上書きする
     * 
     * @return localValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }
    
    /**
     * ValidationメッセージをUTF-8で設定できるようにする
     */
    @Override
    public Validator getValidator() {
        return validator();
    }
    

}
