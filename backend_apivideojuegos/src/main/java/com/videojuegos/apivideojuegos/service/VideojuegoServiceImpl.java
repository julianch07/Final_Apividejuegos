package com.videojuegos.apivideojuegos.service;

import com.videojuegos.apivideojuegos.model.Videojuego;
import com.videojuegos.apivideojuegos.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoServiceImpl implements VideojuegoService {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Override
    public List<Videojuego> getAllVideojuegos() {
        return videojuegoRepository.findAll();
    }

    @Override
    public Videojuego getVideojuegoById(Long id) {
        return videojuegoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El videojuego con ID " + id + " no existe."));
    }

    @Override
    public List<Videojuego> getVideojuegosByCodigo(String codigo) {
        return videojuegoRepository.findByCodigo(codigo);
    }

    @Override
    public Videojuego createVideojuego(Videojuego videojuego) {
        return videojuegoRepository.save(videojuego);
    }

    @Override
    public Videojuego updateVideojuego(Long id, Videojuego nuevoVideojuego) {
        if (videojuegoRepository.existsById(id)) {
            nuevoVideojuego.setId(id);
            return videojuegoRepository.save(nuevoVideojuego);
        } else {
            throw new IllegalArgumentException("El videojuego con ID " + id + " no existe.");
        }
    }

    @Override
    public void deleteVideojuego(Long id) {
        videojuegoRepository.deleteById(id);
    }
}