package com.apri.config;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.apri.common.context.Context;
import com.apri.common.context.RequestContext;
import com.apri.common.filter.LoggingFilter;
import com.apri.common.interceptor.SessionExpireInterceptor;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer  {
    /**
     * Interceptorを登録する
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionExpireInterceptor())
                .addPathPatterns("/**") // 適用対象のパス(パターン)を指定する
                .excludePathPatterns("/static/**"); // 除外するパス(パターン)を指定する
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
     * ValidationメッセージをUTF-8で設定できるようにする
     */
    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //プロパティファイルの名前やディレクトリも変更可能
        messageSource.setBasename("classpath:/ValidationMessages");
        //UTF-8に設定
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }    
}
