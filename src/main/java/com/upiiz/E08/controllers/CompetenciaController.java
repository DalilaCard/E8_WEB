package com.upiiz.E08.controllers;

import com.upiiz.E08.entities.CompetenciaEntity;
import com.upiiz.E08.responses.CompetenciaCustomResponse;
import com.upiiz.E08.services.CompetenciaService;
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
@RequestMapping("/api/v1/competencias")
@Tag(
        name = "Competencias"
)
public class CompetenciaController {
    @Autowired
    CompetenciaService competenciaService;

    @GetMapping
    public ResponseEntity<CompetenciaCustomResponse<List<CompetenciaEntity>>> getCompetencia() {
        List<CompetenciaEntity> competencias = new ArrayList<>();
        Link allClientsLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allClientsLink);
        try {
            competencias = competenciaService.obtenerTodos();
            if (!competencias.isEmpty()) {
                CompetenciaCustomResponse<List<CompetenciaEntity>> response = new CompetenciaCustomResponse<>(1, "Competencias encontradas", competencias, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CompetenciaCustomResponse<>(6, "Competencias no encontradas", competencias, links));
            }
        } catch (Exception e) {
            CompetenciaCustomResponse<List<CompetenciaEntity>> response = new CompetenciaCustomResponse<>(8, "Error interno del servidor", competencias, links);
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Obtener competencias por Id
    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaCustomResponse<CompetenciaEntity>> getCompetenciaById(@PathVariable Long id) {
        Optional<CompetenciaEntity> competencias = null;
        CompetenciaCustomResponse<CompetenciaEntity> response = null;
        Link allCompetenciasLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allCompetenciasLink);
        try {
            competencias = competenciaService.obtenerCompetenciaPorId(id);
            if (competencias.isPresent()) {
                response = new CompetenciaCustomResponse<>(1, "Competencia encontrada", competencias.get(), links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CompetenciaCustomResponse<>(0, "Competencia no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CompetenciaCustomResponse<>(0, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Guardar Competencia
    @PostMapping
    public ResponseEntity<CompetenciaCustomResponse<CompetenciaEntity>> crearCompetencia(@RequestBody CompetenciaEntity competencia) {
        Link allClientsLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allClientsLink);
        try {
            CompetenciaEntity competenciasEntity = competenciaService.guardarCompetencia(competencia);
            if (competenciasEntity != null) {
                CompetenciaCustomResponse<CompetenciaEntity> response = new CompetenciaCustomResponse<>(1, "Competencia creada", competenciasEntity, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CompetenciaCustomResponse<>(6, "Competencia no encontrada", competenciasEntity, links));
            }
        } catch (Exception e) {
            CompetenciaCustomResponse<CompetenciaEntity> response = new CompetenciaCustomResponse<>(8, "Error interno del servidor", null, links);
            System.out.println("Error en Post Competencia: "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Eliminar Competencia
    @DeleteMapping("/{id}")
    public ResponseEntity<CompetenciaCustomResponse<CompetenciaEntity>> deleteCompetenciaById(@PathVariable Long id) {
        Optional<CompetenciaEntity> competencias = null;
        CompetenciaCustomResponse<CompetenciaEntity> response = null;
        Link allCompetenciasLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allCompetenciasLink);

        try {
            competencias = competenciaService.obtenerCompetenciaPorId(id);
            if (competencias.isPresent()) {
                competenciaService.deleteCompetencia(id);
                response = new CompetenciaCustomResponse<>(1, "Competencia eliminada", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CompetenciaCustomResponse<>(0, "Competencia no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CompetenciaCustomResponse<>(500, "Error interno del servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Actualizar competencias por id
    @PutMapping("/{id}")
    public ResponseEntity<CompetenciaCustomResponse<CompetenciaEntity>> updateCompetencia(@RequestBody CompetenciaEntity competencia, @PathVariable Long id) {
        Link allCompetenciasLink = linkTo(CompetenciaController.class).withSelfRel();
        List<Link> links = List.of(allCompetenciasLink);
        try {
            competencia.setId(id);
            if (competenciaService.obtenerCompetenciaPorId(id).isPresent()) {
                CompetenciaEntity competenciasEntity = competenciaService.actualizarCompetencia(competencia);
                CompetenciaCustomResponse<CompetenciaEntity> response = new CompetenciaCustomResponse<>(1, "Competencia actualizada", competenciasEntity, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }else{
                CompetenciaCustomResponse<CompetenciaEntity> response = new CompetenciaCustomResponse<>(0, "Competencia no encontrada", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (Exception e) {
            CompetenciaCustomResponse<CompetenciaEntity> response = new CompetenciaCustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
