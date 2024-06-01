package com.videojuegos.apivideojuegos.service;

import com.videojuegos.apivideojuegos.model.Videojuego;
import java.util.List;

public interface VideojuegoService {
    List<Videojuego> getAllVideojuegos();
    Videojuego getVideojuegoById(Long videojuegoId);
    List<Videojuego> getVideojuegosByCodigo(String codigo); // Agregar este método
    Videojuego createVideojuego(Videojuego videojuego);
    Videojuego updateVideojuego(Long videojuegoId, Videojuego videojuegoDetails);
    void deleteVideojuego(Long videojuegoId);
}
