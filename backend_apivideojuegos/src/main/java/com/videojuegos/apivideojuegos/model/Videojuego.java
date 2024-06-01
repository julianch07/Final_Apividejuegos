package com.videojuegos.apivideojuegos.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "videojuego")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videojuego {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private Double precio;

    private String titulo;

    private String genero;

    private String plataforma;

    private String desarrollador;

    private String descripcion;

}