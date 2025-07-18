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
 

     
   public void annulerLivraisonParLivreur(Long livraisonId,Long userId, CauseAnnulationLivreur cause){
          
        
                 User  user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user introuvable"));

                 Livraison  livraison=livraisonRepository.findById(livraisonId).orElseThrow(() -> new RuntimeException("livraison introuvable"));

            if (!Set.of(UserRole.LIVREUR_OCCASIONNEL, UserRole.LIVREUR_PERMANENT).contains(user.getRole()))
             {
               throw new RuntimeException("Rôle non autorisé pour annuler livraison");
             }
                  if (!Set.of(LivraisonStatus.CREER, LivraisonStatus.EN_COURS).contains(livraison.getStatus())) 
                  {
                    throw new RuntimeException("status non autorise");
                  }
                  if (cause.isCauseClient()){
                  System.out.println("la faute est de cote client");
                  livraison.setStatus(LivraisonStatus.ANNULER);
                  DemandeLivraison demande= livraison.getDemandeDeLivraison();
                  demande.setStatus(DemandeLivraisonStatus.ANNULER);
                  livraison.setDemandeDeLivraison(demande);
                  livraisonRepository.save(livraison);
                  }
                  else{
                    System.out.println("la faute est de cote livreur");
                    livraison.setStatus(LivraisonStatus.CREER);
                    livraisonRepository.save(livraison);

                  }
                }


    public void livraisonEnCours(Long livraisonId , Long userId,Livraison livraison){
       
      {
         User  livreur=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user  introuvable"));

        Livraison  livraison1=livraisonRepository.findById(livraisonId).orElseThrow(() -> new RuntimeException("livraison introuvable"));

        
        if (!Set.of(UserRole.LIVREUR_PERMANENT,UserRole.LIVREUR_OCCASIONNEL).contains(livreur.getRole()))
         {
            throw new IllegalArgumentException("Rôle non autorisé pour accepter  livraison");
          }

          if(livraison.getLivreur() == null || !livraison.getLivreur().getId().equals(userId)){
            throw new IllegalArgumentException("ce n est pas le livreur assigner");
          }

          livraison.setStatus(LivraisonStatus.EN_COURS);
          livraisonRepository.save(livraison1);

      }


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
          livraison.setStatus(LivraisonStatus.SUCCES);
          DemandeLivraison demande= livraison.getDemandeDeLivraison();
          demande.setStatus(DemandeLivraisonStatus.SUCCES);
          livraison.setDemandeDeLivraison(demande);
          livreur.setDisponible(true);
          livraisonRepository.save(livraison);

      }
}