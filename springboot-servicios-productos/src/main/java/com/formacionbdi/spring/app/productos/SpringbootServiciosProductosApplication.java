package com.formacionbdi.spring.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.formaciondbi.springboot_servicio_commons.models.entity"})
public class SpringbootServiciosProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosProductosApplication.class, args);
	}

}
