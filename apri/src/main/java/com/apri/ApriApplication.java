package com.apri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// Bean名が同じだとエラーが出てしまうので、パス付でBean名を登録させる。
@ComponentScan(nameGenerator = FQCNBeanNameGenerator.class)
public class ApriApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApriApplication.class, args);
	}
}
