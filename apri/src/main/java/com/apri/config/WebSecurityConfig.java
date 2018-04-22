package com.apri.config;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.apri.login.LoginService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	LoginService loginService;
	
	// SpringSecurityの制限を無視してほしい場所の指定
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**","/fonts/**","/jasperreports/**","/webjars/**","/pass/**");
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
		   // インメモリーの定義
//		   .inMemoryAuthentication()
//		   .withUser("user").password(passwordEncoder.encode("password")).roles("USER")
//		   .and()
//		   .withUser("admin").password(passwordEncoder.encode("password")).roles("ADMIN");
		   .authenticationProvider(createAuthProvider());
	}
	
	public DaoAuthenticationProvider createAuthProvider() {
	    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	    authenticationProvider.setUserDetailsService(loginService);
 	    // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
	    authenticationProvider.setPasswordEncoder(passwordEncoder());
	    return authenticationProvider;
	}
	
	public static PasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();
	}	
}
