package com.tiendavinos.proyectofinal.repositorios;


import com.tiendavinos.proyectofinal.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>{
    
}
