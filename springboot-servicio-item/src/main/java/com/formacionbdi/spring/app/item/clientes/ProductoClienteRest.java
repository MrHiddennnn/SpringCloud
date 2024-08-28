package com.formacionbdi.spring.app.item.clientes;

import com.formacionbdi.spring.app.item.models.dto.Producto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Comentado por error al intentar subir 1 backend con diferentes puertos (Descomentar tambien en application.yml)
//@FeignClient(name = "servicio-productos")
@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
    @GetMapping("/listar")
    public List<Producto> listarProductos();

    @GetMapping("/obtenerdetallesporid/{id}")
    public Producto obtenerDetallePorId(@PathVariable("id") Long id);

    @PostMapping("/crearproducto")
    public Producto crearProducto(@RequestBody Producto producto);

    @PutMapping("/editarproducto/{idproducto}")
    public Producto editarProducto(@PathVariable("idproducto") long idProducto, @RequestBody Producto producto);

    @DeleteMapping("/eliminarproducto/{iditem}")
    public void eliminarProducto(@PathVariable("iditem") long idProducto);


}
