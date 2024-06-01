package com.videojuegos.apivideojuegos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videojuegos.apivideojuegos.model.Client;

public interface ClienteRepository extends JpaRepository<Client, Long>{
    
}

