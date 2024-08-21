package com.formaciondbi.spring.app.config.springboot_servicio_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringbootServicioConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioConfigApplication.class, args);
	}

}
