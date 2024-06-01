package com.videojuegos.apivideojuegos.service;
import com.videojuegos.apivideojuegos.model.Venta;

import java.math.BigDecimal;
import java.util.List;

public interface VentaService {
    Venta createVenta(Long clienteId, List<Long> videojuegoIds, BigDecimal total);
    Venta findVentaById(Long id);
    List<Venta> findAllVentas();
    void deleteVentaById(Long id);
    Venta updateVenta(Long id, Venta venta);
}