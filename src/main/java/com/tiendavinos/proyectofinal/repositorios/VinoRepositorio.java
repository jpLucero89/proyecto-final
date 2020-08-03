package com.tiendavinos.proyectofinal.repositorios;


import com.tiendavinos.proyectofinal.entidades.Vino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VinoRepositorio extends JpaRepository<Vino, String> {

    @Query("SELECT c FROM Vino c WHERE c.id = :id")
    public Vino buscarPorId(@Param("id")String id);
    
    
    
}
