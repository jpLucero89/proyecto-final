package com.tiendavinos.proyectofinal.repositorios;


import com.tiendavinos.proyectofinal.entidades.Vino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinoRepositorio extends JpaRepository<Vino, String> {

}
