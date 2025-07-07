package com.example.demo.ModelDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveVehiculeRequestDTO {
 
      @NotBlank(message = "Le matricule est obligatoire")
       private String matricule;

      @NotBlank(message = "Le numéro de série est obligatoire")
      private String numSerie;
      
        private boolean disponible;        
}
