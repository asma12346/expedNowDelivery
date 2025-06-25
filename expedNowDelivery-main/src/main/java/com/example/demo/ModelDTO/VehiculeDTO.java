package com.example.demo.ModelDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class VehiculeDTO {

    private Long id;
    private String matricule;
    private String numSerie;
    private Long livreurId; // Pour éviter de retourner tout l'objet User
    private boolean disponible;

    // Constructeur personnalisé
    public VehiculeDTO(Long id, String matricule, String numSerie, Long livreurId, boolean disponible) {
        this.id = id;
        this.matricule = matricule;
        this.numSerie = numSerie;
        this.livreurId = livreurId;
        this.disponible = disponible;
    }
}



