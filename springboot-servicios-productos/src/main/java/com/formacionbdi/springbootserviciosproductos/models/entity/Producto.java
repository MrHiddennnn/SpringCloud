package com.formacionbdi.springbootserviciosproductos.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "productos")
@Data
public class Producto implements Serializable {
    @Value("${server.port}")
    private static String uwu;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String nombre;
    private Double precio;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    public String getPort(){
        return uwu;
    }

}
