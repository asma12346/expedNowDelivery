package com.example.demo.ModelDTO;

import com.example.demo.ModelDomain.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor

public class UpdatevehiculeRequestDTO {

   @NotBlank(message = "Le matricule est obligatoire")
   private String matricule;

    @NotBlank(message = "Le numéro de série est obligatoire")
    private String numSerie;



     public UpdatevehiculeRequestDTO(String matricule , String numSerie ){

      this.matricule = matricule;

      this.numSerie = numSerie;

     }


     
}
