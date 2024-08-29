package com.formacionbdi.spring.app.productos.models.repository;

import com.formaciondbi.springboot_servicio_commons.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
