package com.example.demo.ModelDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class VehiculeDTO {

    private Long id;
    private String matricule;
    private Long numSerie;
    private Long livreurId; // Pour éviter de retourner tout l'objet User
    private boolean disponible;

    // Constructeur personnalisé
    public VehiculeDTO(Long id, String matricule, Long numSerie, Long livreurId, boolean disponible) {
        this.id = id;
        this.matricule = matricule;
        this.numSerie = numSerie;
        this.livreurId = livreurId;
        this.disponible = disponible;
    }
}



