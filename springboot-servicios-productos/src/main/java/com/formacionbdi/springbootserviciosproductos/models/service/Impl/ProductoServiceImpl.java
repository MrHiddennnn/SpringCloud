package com.formacionbdi.springbootserviciosproductos.models.service.Impl;

import com.formacionbdi.springbootserviciosproductos.models.entity.Producto;
import com.formacionbdi.springbootserviciosproductos.models.repository.ProductoRepository;
import com.formacionbdi.springbootserviciosproductos.models.service.IProductoService;
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
