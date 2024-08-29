package com.formacionbdi.spring.app.item.models.dto;

import com.formaciondbi.springboot_servicio_commons.models.entity.Producto;
import lombok.Data;

@Data
public class Item {
    private Producto producto;
    private Integer cantidad;

    public Item() {
    }

    public Item(Producto producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Double getTotal(){
        return producto.getPrecio() * cantidad.doubleValue();
    }
}
