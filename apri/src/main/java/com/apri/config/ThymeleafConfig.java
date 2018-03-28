package com.apri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.apri.common.customTag.CustomDialect;

@Configuration
public class ThymeleafConfig implements WebMvcConfigurer  {
	
        @Bean
        public ClassLoaderTemplateResolver templateResolver() {
            ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
            classLoaderTemplateResolver.setCacheable(false);
            classLoaderTemplateResolver.setPrefix("templates/");
            classLoaderTemplateResolver.setSuffix(".html");
            classLoaderTemplateResolver.setTemplateMode("HTML");
            classLoaderTemplateResolver.setCacheable(false);
            return classLoaderTemplateResolver;
        }


        @Bean
        public SpringTemplateEngine templateEngine() {
            SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    	templateEngine.setEnableSpringELCompiler(true);
            templateEngine.setTemplateResolver(templateResolver());
            templateEngine.addDialect(new CustomDialect());
            return templateEngine;
        }

        @Bean
        public ThymeleafViewResolver viewResolver() {
            ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
            viewResolver.setTemplateEngine(templateEngine());

            return viewResolver;
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
        }
}
