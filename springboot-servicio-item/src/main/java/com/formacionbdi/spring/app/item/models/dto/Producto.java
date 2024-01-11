package com.formacionbdi.spring.app.item.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Producto {
    private Long id;
    private String nombre;
    private Double precio;
    private Date createAt;
}
