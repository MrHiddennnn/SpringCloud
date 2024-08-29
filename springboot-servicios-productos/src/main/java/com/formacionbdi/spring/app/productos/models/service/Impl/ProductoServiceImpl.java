package com.formacionbdi.spring.app.productos.models.service.Impl;

import com.formacionbdi.spring.app.productos.models.repository.ProductoRepository;
import com.formacionbdi.spring.app.productos.models.service.IProductoService;
import com.formaciondbi.springboot_servicio_commons.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto editarProducto(Producto producto, long idProducto) {
        Producto productoEditar = productoRepository.findById(idProducto).orElse(null);

        productoEditar.setNombre(producto.getNombre());
        productoEditar.setPrecio(producto.getPrecio());

        return productoRepository.save(productoEditar);
    }

    @Override
    public void eliminarProducto(long idProducto) {
        productoRepository.deleteById(idProducto);
    }
}
