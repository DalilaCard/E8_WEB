package com.upiiz.E08.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity

@JsonIgnoreProperties({"jugadores", "entrenadores", "competencias", "ligas"})public class EquipoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private LigaEntity liga;

    @OneToMany(mappedBy = "equipo")
    private List<JugadorEntity> jugadores;

    @OneToMany(mappedBy = "equipo")
    private List<EntrenadorEntity> entrenadores;

    @ManyToMany(mappedBy = "equipos")
    private List<CompetenciaEntity> competencias;
}
