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
@Table(name = "competencias")
@JsonIgnoreProperties("equipos")
public class CompetenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String premio;
    private String inicio;
    private String fin;

    @ManyToMany
    @JoinTable(
            name = "equipo_competencia",
            joinColumns = @JoinColumn(name = "competencia_id"),
            inverseJoinColumns = @JoinColumn(name = "equipo_id")
    )
    private List<EquipoEntity> equipos;

}
