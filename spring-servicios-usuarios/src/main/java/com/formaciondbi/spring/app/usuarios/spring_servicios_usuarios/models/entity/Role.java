package com.formaciondbi.spring.app.usuarios.spring_servicios_usuarios.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idrol;
    @Column(unique = true, length = 30)
    private String nombre;
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
//    private List<Usuario> usuarios;
}
