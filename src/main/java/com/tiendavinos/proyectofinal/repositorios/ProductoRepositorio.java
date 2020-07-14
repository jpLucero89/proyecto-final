package com.tiendavinos.proyectofinal.repositorios;

import com.tiendavinos.proyectofinal.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String> {

}
