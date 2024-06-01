package com.videojuegos.apivideojuegos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.videojuegos.apivideojuegos.model.Videojuego;
import com.videojuegos.apivideojuegos.service.VideojuegoService;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @GetMapping
    public List<Videojuego> getAllVideojuegos() {
        return videojuegoService.getAllVideojuegos();
    }

    @GetMapping("/{videojuegoId}")
    public ResponseEntity<Videojuego> getVideojuegoById(@PathVariable Long videojuegoId) {
        Videojuego videojuego = videojuegoService.getVideojuegoById(videojuegoId);
        return videojuego != null ? ResponseEntity.ok(videojuego) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Videojuego> createVideojuego(@RequestBody Videojuego videojuego) {
        Videojuego nuevoVideojuego = videojuegoService.createVideojuego(videojuego);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVideojuego);
    }

    @PutMapping("/{videojuegoId}")
    public ResponseEntity<Videojuego> updateVideojuego(@PathVariable Long videojuegoId, @RequestBody Videojuego videojuego) {
        Videojuego videojuegoActualizado = videojuegoService.updateVideojuego(videojuegoId, videojuego);
        return videojuegoActualizado != null ? ResponseEntity.ok(videojuegoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{videojuegoId}")
    public ResponseEntity<Void> deleteVideojuego(@PathVariable Long videojuegoId) {
        videojuegoService.deleteVideojuego(videojuegoId);
        return ResponseEntity.noContent().build();
    }
}
