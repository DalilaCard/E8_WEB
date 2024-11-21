package com.upiiz.E08.repositories;

import com.upiiz.E08.entities.CompetenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenciaRepository extends JpaRepository<CompetenciaEntity, Long> {
    //Get by ID
    @Query("SELECT c FROM CompetenciaEntity c WHERE c.id = :id")
    CompetenciaEntity findCompetenciaEntityById(@Param("id") Long id);
}
