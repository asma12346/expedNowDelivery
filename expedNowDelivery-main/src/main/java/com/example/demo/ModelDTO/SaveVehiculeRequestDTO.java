package com.example.demo.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveVehiculeRequestDTO {

        private String matricule;
        private String numSerie;
        private boolean disponible;        
}
