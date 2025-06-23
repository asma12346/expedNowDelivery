package com.example.demo.ModelDTO;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.ModelDomain.DemandeLivraisonStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeLivraisonDTO {
    
    private Long id;


    @NotNull(message = "dateCreationDemande is mandatory")
    private LocalDate dateCreationDemande;
    
    @NotNull(message = "clientId is mandatory")
    private Long clientId; 

    @NotEmpty(message = "colis cannot empty")
    @Valid
    private List<ColisDTO> colis;


    private DemandeLivraisonStatus status;

   @NotNull
    private double latitude;
    
   @NotNull
    private double longitude;

    
}




