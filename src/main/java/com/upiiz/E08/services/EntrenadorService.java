package com.upiiz.E08.services;

import com.upiiz.E08.entities.EntrenadorEntity;
import com.upiiz.E08.repositories.EntrenadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {
    @Autowired
    EntrenadorRepository entrenadorRepository;

    public List<EntrenadorEntity> obtenerTodos() {
        return entrenadorRepository.findAll();
    }

    public EntrenadorEntity guardarEntrenador(EntrenadorEntity entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public Optional<EntrenadorEntity> obtenerEntrenadorPorId(Long id) {
        return Optional.ofNullable(entrenadorRepository.findEntrenadorEntityById(id));
    }

    @Transactional
    public void deleteEntrenador(Long id) {
        entrenadorRepository.deleteById(id);
    }

    public EntrenadorEntity actualizarEntrenador(EntrenadorEntity equipo) {
        return entrenadorRepository.save(equipo);
    }
}
