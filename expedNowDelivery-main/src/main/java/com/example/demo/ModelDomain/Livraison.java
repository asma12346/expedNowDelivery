package com.example.demo.ModelDomain;



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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.example.demo.ModelDomain.DemandeLivraison;
import com.example.demo.ModelDomain.LivraisonStatus;


@Entity
@Getter
@Setter
@NoArgsConstructor

public class Livraison {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       private LivraisonStatus statut;

       private Date datePrevuLivraison;

   @ManyToOne
  @JoinColumn(name = "demande_livraison_id")
  private DemandeLivraison demandeDeLivraison;



   @ManyToOne
   @JoinColumn(name ="user_id")
   private User livreur;


   public Livraison(LivraisonStatus statut, Date datePrevuLivraison, User livreur,DemandeLivraison demandeDeLivraison)
    {
        this.statut = statut;
        this.datePrevuLivraison = datePrevuLivraison;
        this.livreur = livreur;
        this.demandeDeLivraison = demandeDeLivraison;
   }


}