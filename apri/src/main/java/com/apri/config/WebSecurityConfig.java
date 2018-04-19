package com.apri.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.apri.common.context.Context;
import com.apri.common.context.RequestContext;
import com.apri.common.filter.LoggingFilter;
import com.apri.common.interceptor.SessionExpireInterceptor;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	// SpringSecurityの制限を無視してほしい場所の指定
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**","/fonts/**","/jasperreports/**","/webjars/**");
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		   .authorizeRequests() // HttpSecurityからアクセス範囲を決めるオブジェクトの取得
		   .antMatchers("/login").permitAll() // "/"は全ユーザアクセス許可の設定
		   .anyRequest().authenticated(); // 他のURLは認証が必要
		http
//		   .httpBasic(); // BASIC認証の設定
		   .formLogin()  // FORM認証の設定
		       .loginPage("/login") // ログイン画面のURL
		       .usernameParameter("tantousya_id")
		       .passwordParameter("password")
		       .successForwardUrl("/menu") // 認証成功時のURL
		       .failureForwardUrl("/login_failure") // 認証失敗時のURL
		       .permitAll()
		       .and()
		   .logout()
		       .permitAll();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth
		   .inMemoryAuthentication()
//		   .withUser("user").password("password").roles("USER")
		   .withUser("user").password(passwordEncoder.encode("password")).roles("USER")
		   .and()
//		   .withUser("admin").password("password").roles("ADMIN");
		   .withUser("admin").password(passwordEncoder.encode("password")).roles("ADMIN");
		   
		
	}
}
