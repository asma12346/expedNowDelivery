package com.example.demo.ModelDTO;

public class SaveVehiculeRequestDTO {

        private String matricule;
        private String numSerie;
        private boolean disponible;



    public SaveVehiculeRequestDTO(String matricule, String numSerie, boolean disponible){

        this.matricule = matricule;
        this.numSerie = numSerie;
        this.disponible = disponible;

    }
}
