package com.videojuegos.apivideojuegos.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.videojuegos.apivideojuegos.controller.ClientController;
import com.videojuegos.apivideojuegos.model.Client;
import com.videojuegos.apivideojuegos.service.ClienteService;

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
@WebMvcTest(ClientController.class)
public class ClientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @InjectMocks
    private ClientController clientController;

    private Client cliente1;
    private Client cliente2;
    private List<Client> clienteList;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

        cliente1 = new Client(1L, "John Doe", "johndoe@example.com", "3017580088", "Carrera 82A#104 DD 39", "JavaDeveloper2015", 1018226759L);
        cliente2 = new Client(2L, "Jane Doe", "janedoe@example.com", "3126935914", "Carrera 82A#104 DD 38", "jbcm2607", 43914653L);
        clienteList = Arrays.asList(cliente1, cliente2);
    }

    @Test
    public void testGetAllClientes() throws Exception {
        when(clienteService.getAllClientes()).thenReturn(clienteList);

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(cliente1.getClienteId()))
                .andExpect(jsonPath("$[1].id").value(cliente2.getClienteId()));
    }

    @Test
    public void testGetClienteById_Success() throws Exception {
        when(clienteService.getClienteById(anyLong())).thenReturn(cliente1);

        mockMvc.perform(get("/api/clientes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cliente1.getClienteId()));
    }

    @Test
    public void testGetClienteById_NotFound() throws Exception {
        when(clienteService.getClienteById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/api/clientes/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateCliente() throws Exception {
        when(clienteService.createCliente(any(Client.class))).thenReturn(cliente1);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cliente1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(cliente1.getClienteId()));
    }

    @Test
    public void testUpdateClienteById_Success() throws Exception {
        when(clienteService.updateCliente(anyLong(), any(Client.class))).thenReturn(cliente1);

        mockMvc.perform(put("/api/clientes/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cliente1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cliente1.getClienteId()));
    }

    @Test
    public void testUpdateClienteById_NotFound() throws Exception {
        when(clienteService.updateCliente(anyLong(), any(Client.class))).thenReturn(null);

        mockMvc.perform(put("/api/clientes/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cliente1)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteClienteById() throws Exception {
        mockMvc.perform(delete("/api/clientes/{id}", 1L))
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