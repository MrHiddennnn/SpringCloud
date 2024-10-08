package com.formacionbdi.spring.app.productos.models.service;


import com.formaciondbi.springboot_servicio_commons.models.entity.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> findAll();
    public Producto findById(Long id);
    public Producto save(Producto producto);
    public Producto editarProducto(Producto producto, long idProducto);;
    void eliminarProducto(long idProducto);
}
