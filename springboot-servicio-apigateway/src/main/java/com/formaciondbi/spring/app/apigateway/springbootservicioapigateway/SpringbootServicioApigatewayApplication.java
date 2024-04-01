package com.formaciondbi.spring.app.apigateway.springbootservicioapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringbootServicioApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioApigatewayApplication.class, args);
	}

}
