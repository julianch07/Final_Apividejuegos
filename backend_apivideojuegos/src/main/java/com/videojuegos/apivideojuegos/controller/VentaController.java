package com.videojuegos.apivideojuegos.controller;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.videojuegos.apivideojuegos.model.Venta;
import com.videojuegos.apivideojuegos.service.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController extends ApiBaseController{
    
    @Autowired
    private VentaService ventaService;

    @PostMapping
    public Venta createVenta(@RequestParam("clienteId") Long clienteId,
                             @RequestParam("videojuegoIds") List<Long> videojuegoIds,
                             @RequestParam("total") BigDecimal total) {
        return ventaService.createVenta(clienteId, videojuegoIds, total);
    }
    @GetMapping("/{id}")
    public Venta findVentaById(@PathVariable("id") Long id) {
        return ventaService.findVentaById(id);
    }

    @GetMapping
    public List<Venta> findAllVentas() {
        return ventaService.findAllVentas();
    }
    @DeleteMapping("/{id}")
    public void deleteVentaById(@PathVariable("id") Long id) {
        ventaService.deleteVentaById(id);
    }

    @PutMapping("/{id}")
    public Venta updateSale(@PathVariable("id") Long id,
                           @RequestBody Venta venta) {
        return ventaService.updateVenta(id, venta);
    }

}
