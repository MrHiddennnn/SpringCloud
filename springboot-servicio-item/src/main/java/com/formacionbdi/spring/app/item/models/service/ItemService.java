package com.formacionbdi.spring.app.item.models.service;

import com.formacionbdi.spring.app.item.models.dto.Item;
import com.formaciondbi.springboot_servicio_commons.models.entity.Producto;

import java.util.List;

public interface ItemService {
    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
    public Producto guardarProducto(Producto producto);
    public Producto editarProducto(Producto producto, long idProducto);;
    void eliminarProducto(long idProducto);
}
