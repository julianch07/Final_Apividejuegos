package com.videojuegos.apivideojuegos.service;

import com.videojuegos.apivideojuegos.model.Client;
import java.util.List;

public interface ClienteService {
    List<Client> getAllClientes();
    Client getClienteById(Long clienteId);
    Client createCliente(Client cliente);
    Client updateCliente(Long clienteId, Client newcliente);
    void deleteCliente(Long clienteId);
}
