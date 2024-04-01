package com.formacionbdi.spring.app.productos.controllers;

import com.formacionbdi.spring.app.productos.models.entity.Producto;
import com.formacionbdi.spring.app.productos.models.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductoController {
    @Autowired
    private IProductoService iProductoService;
    @Autowired
    private Environment env;
    @GetMapping("/listar")
    public List<Producto> listar(){
        return iProductoService.findAll().stream().map(producto ->{
            producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            //producto.setPort(port);
            return producto;
        }).collect(Collectors.toList());
    }
    @GetMapping("/obtenerdetallesporid/{id}")
    public Producto obtenerDetallePorId(@PathVariable("id") Long id){
        Producto producto = iProductoService.findById(id);
//        try{
//            Thread.sleep(2000L);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return producto;
    }

}
