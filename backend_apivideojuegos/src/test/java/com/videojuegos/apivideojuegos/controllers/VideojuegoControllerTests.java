package com.videojuegos.apivideojuegos.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.videojuegos.apivideojuegos.controller.VideojuegoController;
import com.videojuegos.apivideojuegos.model.Videojuego;
import com.videojuegos.apivideojuegos.service.VideojuegoService;


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
@WebMvcTest(VideojuegoController.class)
public class VideojuegoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideojuegoService videojuegoService;

    @InjectMocks
    private VideojuegoController videojuegoController;

    private Videojuego videojuego1;
    private Videojuego videojuego2;
    private List<Videojuego> videojuegoList;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(videojuegoController).build();

        videojuego1 = new Videojuego();
        videojuego1.setId(1L); 
        videojuego1.setCodigo("VJ001");
        videojuego1.setPrecio(59.99);
        videojuego1.setTitulo("Juego 1");
        videojuego1.setGenero("Acción");
        videojuego1.setPlataforma("Plataforma 1");
        videojuego1.setDesarrollador("Desarrollador 1");
        videojuego1.setDescripcion("Descripción del juego 1");
    
        videojuego2 = new Videojuego();
        videojuego2.setId(2L); 
        videojuego2.setCodigo("VJ002");
        videojuego2.setPrecio(39.99);
        videojuego2.setTitulo("Juego 2");
        videojuego2.setGenero("Aventura");
        videojuego2.setPlataforma("Plataforma 2");
        videojuego2.setDesarrollador("Desarrollador 2");
        videojuego2.setDescripcion("Descripción del juego 2");
    
        videojuegoList = Arrays.asList(videojuego1, videojuego2);
    }

    @Test
    public void testGetAllVideojuegos() throws Exception {
        when(videojuegoService.getAllVideojuegos()).thenReturn(videojuegoList);

        mockMvc.perform(get("/api/videojuegos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(videojuego1.getId()))
                .andExpect(jsonPath("$[1].id").value(videojuego2.getId()));
    }

    @Test
    public void testGetVideojuegoById_Success() throws Exception {
        when(videojuegoService.getVideojuegoById(anyLong())).thenReturn(videojuego1);

        mockMvc.perform(get("/api/videojuegos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(videojuego1.getId()));
    }

    @Test
    public void testGetVideojuegoById_NotFound() throws Exception {
        when(videojuegoService.getVideojuegoById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/api/videojuegos/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateVideojuego() throws Exception {
        when(videojuegoService.createVideojuego(any(Videojuego.class))).thenReturn(videojuego1);

        mockMvc.perform(post("/api/videojuegos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(videojuego1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(videojuego1.getId()));
    }

    @Test
    public void testUpdateVideojuegoById_Success() throws Exception {
        when(videojuegoService.updateVideojuego(anyLong(), any(Videojuego.class))).thenReturn(videojuego1);

        mockMvc.perform(put("/api/videojuegos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(videojuego1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(videojuego1.getId()));
    }

    @Test
    public void testUpdateVideojuegoById_NotFound() throws Exception {
        when(videojuegoService.updateVideojuego(anyLong(), any(Videojuego.class))).thenReturn(null);

        mockMvc.perform(put("/api/videojuegos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(videojuego1)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteVideojuegoById() throws Exception {
        mockMvc.perform(delete("/api/videojuegos/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}