package com.upiiz.E08.controllers;

import com.upiiz.E08.entities.JugadorEntity;
import com.upiiz.E08.responses.JugadorCustomResponse;
import com.upiiz.E08.services.JugadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/jugadores")
@Tag(
        name = "Jugadores"
)
public class JugadorController {
    @Autowired
    JugadorService jugadorService;

    @GetMapping
    public ResponseEntity<JugadorCustomResponse<List<JugadorEntity>>> getJugadores() {
        List<JugadorEntity> jugadores = new ArrayList<>();
        Link allJugadoresLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadoresLink);
        try {
            jugadores = jugadorService.obtenerTodos();
            if (!jugadores.isEmpty()) {
                JugadorCustomResponse<List<JugadorEntity>> response = new JugadorCustomResponse<>(1, "Jugadores encontrados", jugadores, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JugadorCustomResponse<>(6, "Jugadores no encontrados", jugadores, links));
            }
        } catch (Exception e) {
            JugadorCustomResponse<List<JugadorEntity>> response = new JugadorCustomResponse<>(8, "Error interno del servidor", jugadores, links);
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JugadorCustomResponse<JugadorEntity>> getJugadorById(@PathVariable Long id) {
        Optional<JugadorEntity> jugador = Optional.empty();
        Link allJugadoresLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadoresLink);
        try {
            jugador = jugadorService.obtenerJugadorPorId(id);
            if (jugador.isPresent()) {
                JugadorCustomResponse<JugadorEntity> response = new JugadorCustomResponse<>(1, "Jugador encontrado", jugador.get(), links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JugadorCustomResponse<>(0, "Jugador no encontrado", null, links));
            }
        } catch (Exception e) {
            JugadorCustomResponse<JugadorEntity> response = new JugadorCustomResponse<>(8, "Error interno del servidor", null, links);
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<JugadorCustomResponse<JugadorEntity>> crearJugador(@RequestBody JugadorEntity jugador) {
        Link allJugadoresLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadoresLink);
        try {
            JugadorEntity jugadorEntity = jugadorService.guardarJugador(jugador);
            if (jugadorEntity != null) {
                JugadorCustomResponse<JugadorEntity> response = new JugadorCustomResponse<>(1, "Jugador creado", jugadorEntity, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JugadorCustomResponse<>(6, "Error al crear el jugador", jugadorEntity, links));
            }
        } catch (Exception e) {
            JugadorCustomResponse<JugadorEntity> response = new JugadorCustomResponse<>(8, "Error interno del servidor", null, links);
            System.out.println("Error en Post Jugador: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JugadorCustomResponse<JugadorEntity>> deleteJugadorById(@PathVariable Long id) {
        Optional<JugadorEntity> jugador = Optional.empty();
        Link allJugadoresLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadoresLink);
        try {
            jugador = jugadorService.obtenerJugadorPorId(id);
            if (jugador.isPresent()) {
                jugadorService.deleteJugador(id);
                return ResponseEntity.status(HttpStatus.OK).body(new JugadorCustomResponse<>(1, "Jugador eliminado", null, links));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JugadorCustomResponse<>(0, "Jugador no encontrado", null, links));
            }
        } catch (Exception e) {
            JugadorCustomResponse<JugadorEntity> response = new JugadorCustomResponse<>(500, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<JugadorCustomResponse<JugadorEntity>> updateJugador(@RequestBody JugadorEntity jugador, @PathVariable Long id) {
        Link allJugadoresLink = linkTo(JugadorController.class).withSelfRel();
        List<Link> links = List.of(allJugadoresLink);
        try {
            jugador.setId(id);
            if (jugadorService.obtenerJugadorPorId(id).isPresent()) {
                JugadorEntity jugadorActualizado = jugadorService.actualizarJugador(jugador);
                JugadorCustomResponse<JugadorEntity> response = new JugadorCustomResponse<>(1, "Jugador actualizado", jugadorActualizado, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JugadorCustomResponse<>(0, "Jugador no encontrado", null, links));
            }
        } catch (Exception e) {
            JugadorCustomResponse<JugadorEntity> response = new JugadorCustomResponse<>(500, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
