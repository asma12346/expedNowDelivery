package com.example.demo.ModelDomain;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.ModelDomain.DemandeLivraisonStatus;
import com.example.demo.ModelDomain.Livraison;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.demo.ModelDomain.DemandeLivraisonStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class DemandeLivraison {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private DemandeLivraisonStatus status;

    private LocalDate datecreationdemande;
    
    @ManyToOne
    @JoinColumn(name = "client_id" , nullable = false)
    private User client;

    
    @OneToMany(mappedBy = "demandeDeLivraison" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Colis> colis = new ArrayList<>();

       
   @OneToMany(mappedBy = "demandeDeLivraison", cascade = CascadeType.ALL)
   private List<Livraison> livraisons = new ArrayList<>();


    private double latitude;
    private double longitude;
    
public DemandeLivraison(DemandeLivraisonStatus status,double latitude, double longitude, LocalDate datecreationdemande, User client, List<Colis> colis) {
    this.status = status;
    this.datecreationdemande = datecreationdemande;
    this.client = client;
    this.colis = colis;
    this.latitude=latitude;
    this.longitude=longitude;
}




}
