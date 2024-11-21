package com.upiiz.E08.services;

import com.upiiz.E08.entities.EquipoEntity;
import com.upiiz.E08.repositories.EquipoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {
    @Autowired
    EquipoRepository equipoRepository;

    public List<EquipoEntity> obtenerTodos() {
        return equipoRepository.findAll();
    }

    public EquipoEntity guardarEquipo(EquipoEntity equipo) {
        return equipoRepository.save(equipo);
    }

    public Optional<EquipoEntity> obtenerEquipoPorId(Long id) {
        return Optional.ofNullable(equipoRepository.findEquipoEntityById(id));
    }

    @Transactional
    public void deleteEquipo(Long id) {
        equipoRepository.deleteById(id);
    }

    public EquipoEntity actualizarEquipo(EquipoEntity equipo) {
        return equipoRepository.save(equipo);
    }
}
