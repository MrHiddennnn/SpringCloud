package com.formaciondbi.spring.app.usuarios.spring_servicios_usuarios.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idusuario;
    @Column(unique = true, length = 20)
    private String username;
    private String apellido;
    @Column(length = 100, unique = true)
    private String email;
    @Column(length = 60)
    private String password;
    private String nombre;
    private boolean enabled;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;
}
