package com.formacionbdi.spring.app.item.models.service.Impl;

import com.formacionbdi.spring.app.item.clientes.ProductoClienteRest;
import com.formacionbdi.spring.app.item.models.dto.Item;
import com.formacionbdi.spring.app.item.models.service.ItemService;
import com.formaciondbi.springboot_servicio_commons.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class ItemServiceFeign implements ItemService {
    @Autowired
    private ProductoClienteRest clienteFeign;

    @Override
    public List<Item> findAll() {
        return clienteFeign.listarProductos().stream().map(objProducto -> new Item(objProducto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeign.obtenerDetallePorId(id), cantidad);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return clienteFeign.crearProducto(producto);
    }

    @Override
    public Producto editarProducto(Producto producto, long idProducto) {
        return clienteFeign.editarProducto(idProducto, producto);
    }

    @Override
    public void eliminarProducto(long idProducto) {
        clienteFeign.eliminarProducto(idProducto);
    }
}
