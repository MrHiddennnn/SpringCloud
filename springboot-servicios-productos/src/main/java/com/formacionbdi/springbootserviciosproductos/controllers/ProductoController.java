package com.formacionbdi.springbootserviciosproductos.controllers;

import com.formacionbdi.springbootserviciosproductos.models.entity.Producto;
import com.formacionbdi.springbootserviciosproductos.models.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired
    private IProductoService iProductoService;
    @GetMapping("/listar")
    public List<Producto> listar(){
        return iProductoService.findAll();
    }
    @GetMapping("/obtenerdetallesporid/{id}")
    public Producto obtenerDetallePorId(@PathVariable("id") Long id){
        return iProductoService.findById(id);
    }

}
