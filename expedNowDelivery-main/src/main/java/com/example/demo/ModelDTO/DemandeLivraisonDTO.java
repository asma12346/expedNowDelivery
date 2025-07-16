package com.example.demo.ModelDTO;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.ModelDomain.DemandeLivraisonStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeLivraisonDTO {

     @NotBlank(message = "champ obligatoire")
    private String destinataire;
  
  
    @NotBlank(message = "champ obligatoire")
    private String adresse_destinataire;


    @NotBlank(message = "champ obligatoire")
    private String   adresse_depart;

    
    private Long clientId; 

    @NotEmpty(message = "colis cannot empty")
    @Valid
    private List<ColisDTO> colis;

    private double latitude;
    
    private double longitude;

    
}




