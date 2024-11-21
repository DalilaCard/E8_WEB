package com.upiiz.E08.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.upiiz.E08.entities.LigaEntity;
import com.upiiz.E08.responses.LigaCustomResponse;
import com.upiiz.E08.services.LigaService;
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
@RequestMapping("/api/v1/ligas")
@Tag(
        name = "Liga"
)
public class LigaController {
    @Autowired
    LigaService ligaService;

    @GetMapping
    public ResponseEntity<LigaCustomResponse<List<LigaEntity>>> getLigas() {
        List<LigaEntity> ligas = new ArrayList<>();
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);
        try {
            ligas = ligaService.obtenerTodos();
            if (!ligas.isEmpty()) {
                LigaCustomResponse<List<LigaEntity>> response = new LigaCustomResponse<>(1, "Ligas encontradas", ligas, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LigaCustomResponse<>(6, "Ligas no encontradas", ligas, links));
            }
        } catch (Exception e) {
            LigaCustomResponse<List<LigaEntity>> response = new LigaCustomResponse<>(8, "Error interno del servidor", ligas, links);
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigaCustomResponse<LigaEntity>> getLigaById(@PathVariable Long id) {
        Optional<LigaEntity> liga = Optional.empty();
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);
        try {
            liga = ligaService.obtenerLigaPorId(id);
            if (liga.isPresent()) {
                LigaCustomResponse<LigaEntity> response = new LigaCustomResponse<>(1, "Liga encontrada", liga.get(), links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LigaCustomResponse<>(0, "Liga no encontrada", null, links));
            }
        } catch (Exception e) {
            LigaCustomResponse<LigaEntity> response = new LigaCustomResponse<>(8, "Error interno del servidor", null, links);
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<LigaCustomResponse<LigaEntity>> crearLiga(@RequestBody LigaEntity liga) {
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);
        try {
            LigaEntity ligaEntity = ligaService.guardarLiga(liga);
            if (ligaEntity != null) {
                LigaCustomResponse<LigaEntity> response = new LigaCustomResponse<>(1, "Liga creada", ligaEntity, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LigaCustomResponse<>(6, "Error al crear la liga", ligaEntity, links));
            }
        } catch (Exception e) {
            LigaCustomResponse<LigaEntity> response = new LigaCustomResponse<>(8, "Error interno del servidor", null, links);
            System.out.println("Error en Post Liga: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LigaCustomResponse<LigaEntity>> deleteLigaById(@PathVariable Long id) {
        Optional<LigaEntity> liga = Optional.empty();
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);
        try {
            liga = ligaService.obtenerLigaPorId(id);
            if (liga.isPresent()) {
                ligaService.deleteLiga(id);
                return ResponseEntity.status(HttpStatus.OK).body(new LigaCustomResponse<>(1, "Liga eliminada", null, links));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LigaCustomResponse<>(0, "Liga no encontrada", null, links));
            }
        } catch (Exception e) {
            LigaCustomResponse<LigaEntity> response = new LigaCustomResponse<>(500, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigaCustomResponse<LigaEntity>> updateLiga(@RequestBody LigaEntity liga, @PathVariable Long id) {
        Link allLigasLink = linkTo(LigaController.class).withSelfRel();
        List<Link> links = List.of(allLigasLink);
        try {
            liga.setId(id);
            if (ligaService.obtenerLigaPorId(id).isPresent()) {
                LigaEntity ligaActualizada = ligaService.actualizarLiga(liga);
                LigaCustomResponse<LigaEntity> response = new LigaCustomResponse<>(1, "Liga actualizada", ligaActualizada, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LigaCustomResponse<>(0, "Liga no encontrada", null, links));
            }
        } catch (Exception e) {
            LigaCustomResponse<LigaEntity> response = new LigaCustomResponse<>(500, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
