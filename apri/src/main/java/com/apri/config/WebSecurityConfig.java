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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.apri.common.context.Context;
import com.apri.common.context.RequestContext;
import com.apri.common.filter.LoggingFilter;
import com.apri.common.interceptor.SessionExpireInterceptor;
import com.apri.login.LoginService;
import com.apri.login.MyLogoutHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
    private MyLogoutHandler myLogoutHandler;
	
	// SpringSecurityの制限を無視してほしい場所の指定
    @Override
    public void configure(WebSecurity web) throws Exception {
    	// /**を追加すると、configure()認証の記述をしても、実施されなくなる。各画面をテストする時は便利。
//        web.ignoring().antMatchers("/static/**","/fonts/**","/jasperreports/**","/webjars/**","/pass/**","/**");
      web.ignoring().antMatchers("/static/**","/fonts/**","/jasperreports/**","/webjars/**","/pass/**");
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		   .authorizeRequests() // HttpSecurityからアクセス範囲を決めるオブジェクトの取得
		   .antMatchers("/login").permitAll() // "/login"はログイン画面だけ全ユーザアクセス許可の設定
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
		   	   .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウト時のURL。このURLとログアウトボタンに記述されるURLが一致する場合、自動的にログアウト処理を実施してくれる。
		   	                                                               // 一致しない場合は、ログアウトボタンに記述されるURLを普通に処理する。
		   	   .logoutSuccessUrl("/xlsx_pdf") // ログアウント成功時に遷移するURL
		   	   .addLogoutHandler(myLogoutHandler) // ログアウト時の処理を独自に盛り込める。
		   	   .deleteCookies("JSESSIONID") // クッキーから指定したクッキーを削除する。
		   	   .invalidateHttpSession(true) // HTTPセッションを破棄する。
		   	   .permitAll();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth
		   // インメモリーの定義
//		   .inMemoryAuthentication()
//		   .withUser("user").password(passwordEncoder.encode("password")).roles("USER")
//		   .and()
//		   .withUser("admin").password(passwordEncoder.encode("password")).roles("ADMIN");
		   // 入力したパスワードをそのまま使用する場合は、以下の処理を実施する。
		   .userDetailsService(loginService);
		   // 入力したパスワードをハッシュ化した値でパスワード認証を行う場合は、以下のコメントを実施する。
//		   .authenticationProvider(createAuthProvider());
	}
	
	public DaoAuthenticationProvider createAuthProvider() {
	    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	    authenticationProvider.setUserDetailsService(loginService);
 	    // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
	    authenticationProvider.setPasswordEncoder(passwordEncoder());
	    return authenticationProvider;
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		   // 入力したパスワードをそのまま使用する場合は、以下の処理を実施する。
		  return NoOpPasswordEncoder.getInstance();
		   // 入力したパスワードをハッシュ化した値でパスワード認証を行う場合は、以下のコメントを実施する。
//	      return new BCryptPasswordEncoder();
	}	
}
