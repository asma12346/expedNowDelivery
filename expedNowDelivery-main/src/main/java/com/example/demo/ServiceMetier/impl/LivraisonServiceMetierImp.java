package com.example.demo.ServiceMetier.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.ModelDomain.DemandeLivraison;
import com.example.demo.ModelDomain.DemandeLivraisonStatus;
import com.example.demo.ModelDomain.Livraison;
import com.example.demo.ModelDomain.LivraisonStatus;
import com.example.demo.ModelDomain.User;
import com.example.demo.ModelDomain.UserRole;
import com.example.demo.repository.DemandeLivraisonRepository;
import com.example.demo.repository.LivraisonRepository;
import com.example.demo.repository.UserRepository;

import lombok.Getter;
import lombok.Setter;

import com.example.demo.ServiceMetier.*;


@Getter
@Setter
@Service
public class LivraisonServiceMetierImp  implements LivraisonServiceMetier
{

  private final DemandeLivraisonServiceMetier demandeLivraison;
  private final LivraisonRepository livraisonRepository;
  private final UserMetierService userMetierService;
  private final UserRepository userRepository;
  private final DemandeLivraisonRepository demandeLivraisonRepository;




  public LivraisonServiceMetierImp(DemandeLivraisonServiceMetier demandeLivraison,DemandeLivraisonRepository demandeLivraisonRepository, UserRepository userRepository, LivraisonRepository livraisonRepository, UserMetierService userMetierService){
    this.demandeLivraison = demandeLivraison;
    this.livraisonRepository = livraisonRepository;
    this.userMetierService = userMetierService;
    this.userRepository = userRepository;
    this.demandeLivraisonRepository = demandeLivraisonRepository;
  }
 

      public void assignerLivreurProcheEtChangerStatut(Long livraisonId) {
    Livraison livraison = livraisonRepository.findById(livraisonId)
            .orElseThrow(() -> new RuntimeException("Livraison introuvable."));

    if (!livraison.getStatut().equals(LivraisonStatus.CREER)) {
        throw new IllegalStateException("La livraison n'est pas dans un état assignable.");
    }

    DemandeLivraison demande = livraison.getDemandeDeLivraison();
    if (demande == null) {
        throw new RuntimeException("Aucune demande associée à cette livraison.");
    }

    double latitudeClient = demande.getLatitude();
    double longitudeClient = demande.getLongitude();

    Optional<User> livreurPlusProche = userMetierService.getLivreurDispoEtProche(latitudeClient, longitudeClient);

    if (livreurPlusProche.isPresent()) {
        User livreur = livreurPlusProche.get();

        if (!Set.of(UserRole.LIVREUR_PERMANENT, UserRole.LIVREUR_OCCASIONNEL).contains(livreur.getRole())) {
            throw new RuntimeException("Le rôle du livreur n'est pas valide.");
        }

        livraison.setLivreur(livreur);
        livraison.setStatut(LivraisonStatus.EN_COURS);

        demande.setStatus(DemandeLivraisonStatus.EN_COURS);

        livraisonRepository.save(livraison);
        demandeLivraisonRepository.save(demande);
    } else {
        throw new RuntimeException("Aucun livreur disponible à proximité.");
    }
}

   public void annulerLivraisonParLivreur(Long livraisonId,Long userId){
          
        
                 User  user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user introuvable"));

                 Livraison  livraison=livraisonRepository.findById(livraisonId).orElseThrow(() -> new RuntimeException("livraison introuvable"));

            if (!Set.of(UserRole.LIVREUR_OCCASIONNEL, UserRole.LIVREUR_PERMANENT).contains(user.getRole()))
             {
               throw new RuntimeException("Rôle non autorisé pour annuler livraison");
             }
                  if (!Set.of(LivraisonStatus.CREER, LivraisonStatus.EN_COURS).contains(livraison.getStatut())) 
                  {
                    throw new RuntimeException("status non autorise");
                  }
     
                  livraison.setStatut(LivraisonStatus.ANNULER);
                  DemandeLivraison demande= livraison.getDemandeDeLivraison();
                  demande.setStatus(DemandeLivraisonStatus.ANNULER);
                  livraison.setDemandeDeLivraison(demande);
                  livraisonRepository.save(livraison);

                }

      public void livraisonAchever(Long livraisonId , Long userId){

        User  livreur=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user  introuvable"));

        Livraison  livraison=livraisonRepository.findById(livraisonId).orElseThrow(() -> new RuntimeException("livraison introuvable"));

        
        if (!Set.of(UserRole.LIVREUR_PERMANENT,UserRole.LIVREUR_OCCASIONNEL).contains(livreur.getRole()))
         {
            throw new IllegalArgumentException("Rôle non autorisé pour terminer livraison");
          }
           
          if (!livraison.getLivreur().getId().equals(livreur.getId()))
          
          { 
            throw new RuntimeException("Ce livreur n'est pas assigné à cette livraison.");
          }
          livraison.setStatut(LivraisonStatus.SUCCES);
          DemandeLivraison demande= livraison.getDemandeDeLivraison();
          demande.setStatus(DemandeLivraisonStatus.SUCCES);
          livraison.setDemandeDeLivraison(demande);
          livraisonRepository.save(livraison);

      }
}