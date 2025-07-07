package com.example.demo.ModelDomain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricule;

    @Column(unique = true, nullable = false)
    private String numSerie;

    @Column(nullable = false)
    private boolean disponible = true;

    @OneToOne
    @JoinColumn(name = "livreur_id")
    private User livreur;

    public Vehicule(Long id, String matricule, String numSerie, User livreur, boolean disponible) {
        this.id = id;
        this.matricule = matricule;
        this.numSerie = numSerie;
        this.livreur = livreur;
        this.disponible = disponible;
    }
}
