package com.tiendavinos.proyectofinal.repositorios;


import com.tiendavinos.proyectofinal.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String> {

}
