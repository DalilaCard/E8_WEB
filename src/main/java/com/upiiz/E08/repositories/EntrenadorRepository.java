package com.upiiz.E08.repositories;

import com.upiiz.E08.entities.EntrenadorEntity;
import com.upiiz.E08.entities.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<EntrenadorEntity, Long> {
    //Get by ID
    @Query("SELECT en FROM EntrenadorEntity en WHERE en.id = :id")
    EntrenadorEntity findEntrenadorEntityById(@Param("id") Long id);
}
