package com.formacionbdi.spring.app.productos.models.service.Impl;

import com.formacionbdi.spring.app.productos.models.entity.Producto;
import com.formacionbdi.spring.app.productos.models.repository.ProductoRepository;
import com.formacionbdi.spring.app.productos.models.service.IProductoService;
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
}
