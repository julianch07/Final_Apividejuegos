package com.videojuegos.apivideojuegos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videojuegos.apivideojuegos.model.Videojuego;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long>{
    List<Videojuego> findByCodigo(String codigo);
}
