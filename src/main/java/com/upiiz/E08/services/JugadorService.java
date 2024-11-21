package com.upiiz.E08.services;

import com.upiiz.E08.entities.JugadorEntity;
import com.upiiz.E08.repositories.JugadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {
    @Autowired
    JugadorRepository jugadorRepository;

    public List<JugadorEntity> obtenerTodos() {
        return jugadorRepository.findAll();
    }

    public JugadorEntity guardarJugador(JugadorEntity jugador) {
        return jugadorRepository.save(jugador);
    }

    public Optional<JugadorEntity> obtenerJugadorPorId(Long id) {
        return Optional.ofNullable(jugadorRepository.findJugadorEntityById(id));
    }

    @Transactional
    public void deleteJugador(Long id) {
        jugadorRepository.deleteById(id);
    }

    public JugadorEntity actualizarJugador(JugadorEntity equipo) {
        return jugadorRepository.save(equipo);
    }
}
