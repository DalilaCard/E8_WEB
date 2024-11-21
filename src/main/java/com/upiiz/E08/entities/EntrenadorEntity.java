package com.upiiz.E08.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "equipos")
@JsonIgnoreProperties({"jugadores", "entrenadores", "competencias"})
public class EntrenadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String edad;
    private String nacionalidad;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private EquipoEntity equipo;

}
