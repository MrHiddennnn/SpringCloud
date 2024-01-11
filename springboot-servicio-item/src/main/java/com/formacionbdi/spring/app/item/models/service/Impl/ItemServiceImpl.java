package com.formacionbdi.spring.app.item.models.service.Impl;

import com.formacionbdi.spring.app.item.models.dto.Item;
import com.formacionbdi.spring.app.item.models.dto.Producto;
import com.formacionbdi.spring.app.item.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RestTemplate registrarRestTemplate;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(registrarRestTemplate.getForObject("http://localhost:2020/listar", Producto[].class));
        return productos.stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Producto producto = registrarRestTemplate.getForObject("http://localhost:2020/obtenerdetallesporid/{id}", Producto.class, pathVariables);
        return new Item(producto, cantidad);
    }
}
