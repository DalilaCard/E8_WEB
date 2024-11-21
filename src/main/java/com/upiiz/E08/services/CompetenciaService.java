package com.upiiz.E08.services;

import com.upiiz.E08.repositories.CompetenciaRepository;
import com.upiiz.E08.entities.CompetenciaEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenciaService {
    @Autowired
    CompetenciaRepository competenciaRepository;

    public List<CompetenciaEntity> obtenerTodos() {
        return competenciaRepository.findAll();
    }

    public CompetenciaEntity guardarCompetencia(CompetenciaEntity competencia) {
        return competenciaRepository.save(competencia);
    }

    public Optional<CompetenciaEntity> obtenerCompetenciaPorId(Long id) {
        return Optional.ofNullable(competenciaRepository.findCompetenciaEntityById(id));
    }

    @Transactional
    public void deleteCompetencia(Long id) {
        competenciaRepository.deleteById(id);
    }

    public CompetenciaEntity actualizarCompetencia(CompetenciaEntity equipo) {
        return competenciaRepository.save(equipo);
    }
}

