package com.apri.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.apri.common.LoggingFilter;
import com.apri.common.SessionExpireInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer  {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionExpireInterceptor())
                .addPathPatterns("/**") // 適用対象のパス(パターン)を指定する
                .excludePathPatterns("/static/**"); // 除外するパス(パターン)を指定する
    }
    
    @Bean
	public FilterRegistrationBean commonFilter1() {		
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new LoggingFilter());
		bean.setOrder(1);
		return bean;
	}
}
