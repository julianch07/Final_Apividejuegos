package com.videojuegos.apivideojuegos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.videojuegos.apivideojuegos.model.Venta;
public interface VentaRepository extends JpaRepository<Venta, Long>{
    
}
