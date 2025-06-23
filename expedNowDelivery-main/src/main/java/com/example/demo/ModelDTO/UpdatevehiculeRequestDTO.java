package com.example.demo.ModelDTO;

import com.example.demo.ModelDomain.User;



public class UpdatevehiculeRequestDTO {

    
     private String matricule;
     private String numSerie;
     private Long livreurId;


     public UpdatevehiculeRequestDTO(String matricule , String numSerie  , Long livreurId){

        this.matricule = matricule;
        this.numSerie = numSerie;
        this.livreurId = livreurId;
     }


     
}
