package com.example.demo.ModelDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.ModelDomain.*;

public class SaveDemandeRequestDTO {



    private LocalDate datecreationdemande;
    
    @ManyToOne
    @JoinColumn(name = "client_id" , nullable = false)
    private User client;

    
    @OneToMany(mappedBy = "demandeDeLivraison" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Colis> colis ;

    private double latitude;
    private double longitude;
       
    
public  SaveDemandeRequestDTO( LocalDate datecreationdemande, User client, List<Colis> colis, double latitude, double longitude) {
   
    this.datecreationdemande = datecreationdemande;
    this.client = client;
    this.colis = colis;
    this.latitude = latitude;
    this.longitude = longitude;

  
}
}






