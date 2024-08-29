package com.formacionbdi.spring.app.item.models.service.Impl;

import com.formacionbdi.spring.app.item.models.dto.Item;
import com.formacionbdi.spring.app.item.models.service.ItemService;
import com.formaciondbi.springboot_servicio_commons.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class));
        return productos.stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Producto producto = clienteRest.getForObject("http://servicio-productos/obtenerdetallesporid/{id}", Producto.class, pathVariables);
        return new Item(producto, cantidad);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        HttpEntity<Producto> httpEntity = new HttpEntity<>(producto);
        return clienteRest.exchange("http://servicio-productos/crearproducto", HttpMethod.POST, httpEntity, Producto.class).getBody();
    }

    @Override
    public Producto editarProducto(Producto producto, long idProducto) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", String.valueOf(idProducto));
        HttpEntity<Producto> httpEntity = new HttpEntity<>(producto);
        return clienteRest.exchange("http://servicio-productos/editarproducto/{id}", HttpMethod.PUT, httpEntity, Producto.class, pathVariables).getBody();
    }

    @Override
    public void eliminarProducto(long idProducto) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", String.valueOf(idProducto));

        clienteRest.delete("http://servicio-productos/eliminarproducto/{id}", pathVariables);
    }
}
