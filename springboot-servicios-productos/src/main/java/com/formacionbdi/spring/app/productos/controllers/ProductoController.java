package com.formacionbdi.spring.app.productos.controllers;

import com.formacionbdi.spring.app.productos.models.service.IProductoService;
import com.formaciondbi.springboot_servicio_commons.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
public class ProductoController {
    @Autowired
    private IProductoService iProductoService;
    @Autowired
    private Environment env;

    @GetMapping("/listar")
    public List<Producto> listar() {
        return iProductoService.findAll().stream().map(producto -> {
            producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            return producto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/obtenerdetallesporid/{id}")
    public Producto obtenerDetallePorId(@PathVariable("id") Long id) throws InterruptedException {
        if (id.equals(10L)) {
            throw new IllegalStateException("Producto no encontrado");
        }
        if (id.equals(7L)) {
            TimeUnit.SECONDS.sleep(5L);
        }
        return iProductoService.findById(id);
    }

    @PostMapping("/crearproducto")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crearProducto(@RequestBody Producto producto) {
        return iProductoService.save(producto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/editarproducto/{idproducto}")
    public Producto editarProducto(@PathVariable("idproducto") long idProducto, @RequestBody Producto producto) {
        return iProductoService.editarProducto(producto, idProducto);
    }

    @DeleteMapping("/eliminarproducto/{idproducto}")
    @ResponseStatus(HttpStatus.CREATED)
    public void eliminarProducto(@PathVariable("idproducto") long idProducto) {
        iProductoService.eliminarProducto(idProducto);
    }
}
