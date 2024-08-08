package com.formacionbdi.spring.app.item.controller;

import com.formacionbdi.spring.app.item.models.dto.Item;
import com.formacionbdi.spring.app.item.models.dto.Producto;
import com.formacionbdi.spring.app.item.models.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class ItemController {
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;
    @Autowired
    private ItemService itemService;

    @GetMapping("/listadoitems")
    public List<Item> listadoItems(@RequestHeader(value = "token-request", required = false) String token, @RequestParam(value = "nombre", required = false) String nombre) {
        log.info("token-request " + token);
        log.info("nombre " + nombre);
        return itemService.findAll();
    }

    @GetMapping("/informacionitem/{id}/cantidad/{cantidad}")
//    @CircuitBreaker(name = "miCircuito", fallbackMethod = "metodoAlternativo")
    public Item informacionItem(@PathVariable("id") Long id, @PathVariable("cantidad") Integer cantidad) {
        return circuitBreakerFactory.create("items").run(() -> itemService.findById(id, cantidad)
                , e -> metodoAlternativo(id, cantidad, e)
        );
    }

    @GetMapping("/informacionitem2/{id}/cantidad/{cantidad}")
    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
    public Item informacionItem2(@PathVariable("id") Long id, @PathVariable("cantidad") Integer cantidad) {
        return itemService.findById(id, cantidad);
    }

    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo2")
    @GetMapping("/informacionitem3/{id}/cantidad/{cantidad}")
    @TimeLimiter(name = "items", fallbackMethod = "metodoAlternativo2")
    public CompletableFuture<Item> informacionItem3(@PathVariable("id") Long id, @PathVariable("cantidad") Integer cantidad) {
        return CompletableFuture.supplyAsync(() -> itemService.findById(id, cantidad));
    }

    @GetMapping("/informacionitemanotacion/{id}/cantidad/{cantidad}")
    public Item informacionItemAnotacion(@PathVariable("id") Long id, @PathVariable("cantidad") Integer cantidad) {
        return itemService.findById(id, cantidad);
    }

    public Item metodoAlternativo(Long id, Integer cantidad, Throwable e) {
        log.info("El error es: ", e);
        Item item = new Item();
        item.setCantidad(cantidad);

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre("Camara Sony");
        producto.setPrecio(500.00);

        item.setProducto(producto);
        return item;
    }

    public CompletableFuture<Item> metodoAlternativo2(Long id, Integer cantidad, Throwable e) {
        log.info("El error es: ", e);
        Item item = new Item();
        item.setCantidad(cantidad);

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre("Camara Sony");
        producto.setPrecio(500.00);

        item.setProducto(producto);
        return CompletableFuture.supplyAsync(() -> item);
    }
}
