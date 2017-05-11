package org.easyrules.spring.boot.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableAspectJAutoProxy (proxyTargetClass = true)
@ComponentScan ("org.easyrules.spring.boot.integration")
public class SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}
}
