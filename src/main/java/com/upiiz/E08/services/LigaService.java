package com.upiiz.E08.services;

import com.upiiz.E08.entities.LigaEntity;
import com.upiiz.E08.repositories.LigaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigaService {
    @Autowired
    LigaRepository ligaRepository;

    public List<LigaEntity> obtenerTodos() {
        return ligaRepository.findAll();
    }

    public LigaEntity guardarLiga(LigaEntity competencia) {
        return ligaRepository.save(competencia);
    }

    public Optional<LigaEntity> obtenerLigaPorId(Long id) {
        return Optional.ofNullable(ligaRepository.findLigaEntityById(id));
    }

    @Transactional
    public void deleteLiga(Long id) {
        ligaRepository.deleteById(id);
    }

    public LigaEntity actualizarLiga(LigaEntity equipo) {
        return ligaRepository.save(equipo);
    }
}
