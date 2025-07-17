package com.example.demo.ServiceMetier.impl;

public enum CauseAnnulationLivreur {

    CLIENT_INJOIGNABLE(true),        // Faute du client
    ADRESSE_INTRROUVABLE(true),      // Faute du client
    RETARD_IMPORTANT(false),         // Faute du livreur
    PROBLEME_PERSONNEL(false),       // Faute du livreur
    AUTRE(false);


    private final boolean CauseAnnulation;


     CauseAnnulationLivreur(boolean CauseAnnulation){

        this.CauseAnnulation= CauseAnnulation;
    }

    public boolean isCauseClient(){
        return CauseAnnulation;
    }
}
