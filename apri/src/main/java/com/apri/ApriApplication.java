package com.apri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;


@SpringBootApplication
// Bean名が同じだとエラーが出てしまうので、パス付でBean名を登録させる。
@ComponentScan(nameGenerator = FQCNBeanNameGenerator.class)
/** Tomcatを使用する場合   **/
public class ApriApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApriApplication.class, args);
	}

	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { 
		return application.sources(ApriApplication.class); 
	} 
	
	// ブラウザで初回アクセス時に、jsessinidが追加される対応
	// jsessinidが追加されるとJquery、CSSが正しく読み込めないので、削除する。
	@Bean
    public ServletContextInitializer servletContextInitializer() {
        ServletContextInitializer servletContextInitializer =
                new ServletContextInitializer() {

                    @Override
                    public void onStartup(ServletContext servletContext)
                            throws ServletException {
                        servletContext.setSessionTrackingModes(
                                Collections.singleton(SessionTrackingMode.COOKIE));
                    }
                };

        return servletContextInitializer;
    }
	
}

/** Tomcatを使用しない場合
public class ApriApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApriApplication.class, args);
	}
}
**/

