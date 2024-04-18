package com.formacionbdi.spring.app.item.controller;

import com.formacionbdi.spring.app.item.models.dto.Item;
import com.formacionbdi.spring.app.item.models.dto.Producto;
import com.formacionbdi.spring.app.item.models.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/listadoitems")
    public List<Item> listadoItems(@RequestHeader("token-request") String token, @RequestParam("nombre") String nombre) {
        log.info("token-request " + token);
        log.info("nombre " + nombre);
        return itemService.findAll();
    }

    @GetMapping("/informacionitem/{id}/cantidad/{cantidad}")
    @CircuitBreaker(name = "miCircuito", fallbackMethod = "metodoAlternativo")
    public Item informacionItem(@PathVariable("id") Long id, @PathVariable("cantidad") Integer cantidad) {
        return itemService.findById(id, cantidad);
    }

    public Item metodoAlternativo(Long id, Integer cantidad, Throwable throwable) throws Throwable {
        Item item = new Item();
        item.setCantidad(cantidad);

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre("Camara Sony");
        producto.setPrecio(500.00);

        item.setProducto(producto);
        return item;
    }
}
