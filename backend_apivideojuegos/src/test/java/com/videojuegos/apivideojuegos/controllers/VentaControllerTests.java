package com.videojuegos.apivideojuegos.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.videojuegos.apivideojuegos.controller.VentaController;
import com.videojuegos.apivideojuegos.model.Client;
import com.videojuegos.apivideojuegos.model.Venta;
import com.videojuegos.apivideojuegos.model.Videojuego;
import com.videojuegos.apivideojuegos.service.VentaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.extension.ExtendWith;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(VentaController.class)
public class VentaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaService ventaService;

    @InjectMocks
    private VentaController ventaController;

    private Venta venta1;
    private Venta venta2;
    private List<Venta> ventaList;
    private Client cliente;
    private Videojuego videojuego1;
    private Videojuego videojuego2;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ventaController).build();

        cliente = new Client(1L, "John Doe", "johndoe@example.com", "3017580088", "Carrera 82A#104 DD 39", "JavaDeveloper2015", 1018226759L);
        videojuego1 = new Videojuego(1L, "VJ001", 59.99, "Juego 1", "Acción", "Plataforma 1", "Desarrollador 1", "Descripción del juego 1");
        videojuego2 = new Videojuego(2L, "VJ002", 39.99, "Juego 2", "Aventura", "Plataforma 2", "Desarrollador 2", "Descripción del juego 2");

        venta1 = new Venta(1L, cliente, Arrays.asList(videojuego1, videojuego2), new BigDecimal("100.00"), LocalDateTime.now());
        venta2 = new Venta(2L, cliente, Arrays.asList(videojuego1), new BigDecimal("50.00"), LocalDateTime.now());
        ventaList = Arrays.asList(venta1, venta2);
    }

    @Test
    public void testCreateVenta() throws Exception {
        when(ventaService.createVenta(anyLong(), anyList(), any(BigDecimal.class))).thenReturn(venta1);

        mockMvc.perform(post("/api/ventas")
                        .param("clienteId", "1")
                        .param("videojuegoIds", "1,2")
                        .param("total", "100.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(venta1.getId()));
    }

    @Test
    public void testFindVentaById_Success() throws Exception {
        when(ventaService.findVentaById(anyLong())).thenReturn(venta1);

        mockMvc.perform(get("/api/ventas/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(venta1.getId()));
    }

    @Test
    public void testFindVentaById_NotFound() throws Exception {
        when(ventaService.findVentaById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/api/ventas/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindAllVentas() throws Exception {
        when(ventaService.findAllVentas()).thenReturn(ventaList);

        mockMvc.perform(get("/api/ventas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(venta1.getId()))
                .andExpect(jsonPath("$[1].id").value(venta2.getId()));
    }

    @Test
    public void testDeleteVentaById() throws Exception {
        mockMvc.perform(delete("/api/ventas/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateVenta_Success() throws Exception {
        when(ventaService.updateVenta(anyLong(), any(Venta.class))).thenReturn(venta1);

        mockMvc.perform(put("/api/ventas/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(venta1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(venta1.getId()));
    }

    @Test
    public void testUpdateVenta_NotFound() throws Exception {
        when(ventaService.updateVenta(anyLong(), any(Venta.class))).thenReturn(null);

        mockMvc.perform(put("/api/ventas/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(venta1)))
                .andExpect(status().isNotFound());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}