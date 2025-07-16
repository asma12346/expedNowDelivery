package com.example.demo.ModelDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.example.demo.ModelDomain.LivraisonStatus;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LivraisonDTO {

    @NotNull(message = "id obligatoire")
    private Long id;
   
    @NotNull
    private LivraisonStatus status;
      
    @NotNull(message = "dateCreationDemande is mandatory")
     private LocalDateTime datePrevuLivraison;
 
     @NotEmpty
    private Long demandeLivraisonId;  
    
   @NotNull
    private Long livreurId;
}
