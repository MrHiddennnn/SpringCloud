package com.formaciondbi.springboot_servicio_commons.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "productos")
@Data
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String nombre;
    private Double precio;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    private Integer port;


}
