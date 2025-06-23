package com.example.demo.ModelDomain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.util.List;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import com.example.demo.ModelDomain.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @NotBlank
     @Column(unique = true)
     private String matricule;
     

     @NotBlank
     @Column(unique = true)
     private String numSerie;

     
     @Column(nullable = false)
     private boolean disponible=true; 

     @OneToOne
     @JoinColumn(name = "livreur_id")
     private User livreur;

     public Vehicule(Long id, String matricule,String numSerie, User livreur, boolean disponible) {
    this.id = id;
    this.matricule = matricule;
    this.livreur = livreur;
    this.disponible = disponible;
    this.numSerie = numSerie;
}

}
