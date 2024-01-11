package com.formacionbdi.springbootserviciosproductos.models.repository;

import com.formacionbdi.springbootserviciosproductos.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
