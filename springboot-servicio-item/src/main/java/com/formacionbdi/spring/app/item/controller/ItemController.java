package com.formacionbdi.spring.app.item.controller;

import com.formacionbdi.spring.app.item.models.dto.Item;
import com.formacionbdi.spring.app.item.models.service.ItemService;
import com.formaciondbi.springboot_servicio_commons.models.entity.Producto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RefreshScope
@RestController
@Slf4j
public class ItemController {
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;
    @Autowired
    private ItemService itemService;
//    @Value("${configuracion.texto}")
//    private String texto;
    @Autowired
    private Environment env;

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
        producto.setNombre("Controlador de errores");
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
        producto.setNombre("Controlador de errores");
        producto.setPrecio(500.00);

        item.setProducto(producto);
        return CompletableFuture.supplyAsync(() -> item);
    }

//    @GetMapping("/obtener-config")
//    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto) {
//        log.info(texto);
//        Map<String, String> json = new HashMap<>();
//        json.put("texto", texto);
//        json.put("puerto", puerto);
//
//        if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
//            json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
//            json.put("autor.email", env.getProperty("configuracion.autor.email"));
//        }
//        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
//    }

    @PostMapping("/crearproducto")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crearProducto(@RequestBody Producto producto) {
        return itemService.guardarProducto(producto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/editarproducto/{idproducto}")
    public Producto editarProducto(@PathVariable("idproducto") long idProducto, @RequestBody Producto producto) {
        return itemService.editarProducto(producto, idProducto);
    }

    @DeleteMapping("/eliminarproducto/{iditem}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(@PathVariable("iditem") long idProducto) {
        itemService.eliminarProducto(idProducto);
    }
}
