package com.videojuegos.apivideojuegos.service;

import com.videojuegos.apivideojuegos.model.Client;
import com.videojuegos.apivideojuegos.model.Venta;
import com.videojuegos.apivideojuegos.model.Videojuego;
import com.videojuegos.apivideojuegos.repository.ClienteRepository;
import com.videojuegos.apivideojuegos.repository.VentaRepository;
import com.videojuegos.apivideojuegos.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

import jakarta.transaction.Transactional;
@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Override
    @Transactional
    public Venta createVenta(Long clienteId, List<Long> videojuegoIds, BigDecimal total) {
        Venta venta = new Venta();
        venta.setTotal(total);

        Client cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente"));
        venta.setCliente(cliente);

        List<Videojuego> videojuegos = videojuegoRepository.findAllById(videojuegoIds);
        venta.setVideojuegos(videojuegos);

        return ventaRepository.save(venta);
    }

    @Override
    public Venta findVentaById(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    @Override
    public List<Venta> findAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public void deleteVentaById(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Venta updateVenta(Long id, Venta venta) {
        Venta ventaExistente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        ventaExistente.setTotal(venta.getTotal());
        ventaExistente.setCliente(venta.getCliente());
        ventaExistente.setVideojuegos(venta.getVideojuegos());

        return ventaRepository.save(ventaExistente);
    }

}