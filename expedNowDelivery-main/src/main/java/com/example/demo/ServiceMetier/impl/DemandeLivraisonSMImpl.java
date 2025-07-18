package com.example.demo.ServiceMetier.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.ModelDomain.Colis;
import com.example.demo.ModelDomain.DemandeLivraison;
import com.example.demo.ModelDomain.DemandeLivraisonStatus;
import com.example.demo.ModelDomain.Livraison;
import com.example.demo.ModelDomain.LivraisonStatus;
import com.example.demo.repository.ColisRepository;
import com.example.demo.repository.DemandeLivraisonRepository;
import com.example.demo.repository.LivraisonRepository;
import com.example.demo.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import com.example.demo.ServiceMetier.*;


import com.example.demo.ModelDomain.User;
import com.example.demo.ModelDomain.UserRole;
import com.example.demo.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.naming.LinkRef;


@Service
public class DemandeLivraisonSMImpl implements DemandeLivraisonServiceMetier{


    private DemandeLivraisonRepository demandeLivraisonRepository;
        private UserMetierService userMetierService;
         private UserRepository userRepository;
         private ColisServiceMetier colisServiceMetier;
         private ColisRepository colisRepository;
         private LivraisonRepository livraisonRepository;

   public DemandeLivraisonSMImpl(DemandeLivraisonRepository demandeLivraisonRepository, LivraisonRepository livraisonRepository,ColisServiceMetier colisServiceMetier,UserRepository userRepository,UserMetierService userMetierService)

          {
           
            this.demandeLivraisonRepository = demandeLivraisonRepository;
            this.userMetierService = userMetierService;
            this.userRepository = userRepository;
            this.colisServiceMetier = colisServiceMetier;
            this.livraisonRepository = livraisonRepository;
            
          }
    
    @Transactional 
    public DemandeLivraison saveDemandeLivraison(DemandeLivraison demande)
          {

            //lier chaque colis a la demande
            if(demande.getColis() != null){
   
                demande.getColis().forEach(c -> c.setDemandeDeLivraison(demande));
                     
            }  

            demande.setStatus(DemandeLivraisonStatus.En_ATTENTE);
            DemandeLivraison demandeL = demandeLivraisonRepository.save(demande);

             Livraison livraison = new Livraison();

             LocalDateTime datePrevue = LocalDateTime.now().plusHours(24);
            
             livraison.setDatePrevuLivraison(datePrevue);
             livraison.setDemandeDeLivraison(demandeL);
             livraison.setStatus(LivraisonStatus.CREER);
            livraison.setLivreur(null); // livreur non encore assigné

             
           Livraison livraisonsaved = livraisonRepository.save(livraison);

           try{

               assignerLivreurProcheEtChangerStatut(livraisonsaved.getId());
              
           } catch (RuntimeException e) {

                 System.out.println("Aucun livreur assigné automatiquement : " + e.getMessage());

           }
           
           return demandeL;
          }
       
           public void assignerLivreurProcheEtChangerStatut(Long livraisonId ) {
    Livraison livraison = livraisonRepository.findById(livraisonId)
            .orElseThrow(() -> new RuntimeException("Livraison introuvable."));

    if (!livraison.getStatus().equals(LivraisonStatus.CREER)) {
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
        livreur.setDisponible(false);
        livraison.setStatus(LivraisonStatus.EN_COURS);

        demande.setStatus(DemandeLivraisonStatus.EN_COURS);

        livraisonRepository.save(livraison);
        demandeLivraisonRepository.save(demande);
    } else {
        throw new RuntimeException("Aucun livreur disponible à proximité.");
    }
}
            public DemandeLivraison updateDemande(Long id, DemandeLivraison updatedDemande) {  

        return demandeLivraisonRepository.findById(id)
        .map(existing -> {
            existing.setStatus(updatedDemande.getStatus());
            existing.setDatecreationdemande(updatedDemande.getDatecreationdemande());
             
          List<Colis> existingColisList = existing.getColis();
          List<Colis> updatedColisList = updatedDemande.getColis();

          for(int i =0 ; i<existingColisList.size();i++){
            
            Colis colisExisting = existingColisList.get(i);
            Colis colisupdated = updatedColisList.get(i);

           colisServiceMetier.updateColis(colisExisting.getId(), colisupdated);
          }


            return demandeLivraisonRepository.save(existing);
        })
        .orElseThrow(() -> new RuntimeException("Demande de livraison non trouvée avec l'ID : " + id));
}
    

      public void annulerDemandeParClient(Long demandeId, Long  userId ) {
    
        DemandeLivraison   demandeLivraison = demandeLivraisonRepository.findById(demandeId).orElseThrow(() -> new RuntimeException("Demande introuvable"));
        
        User    user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user introuvable"));


              if (!Set.of(UserRole.CLIENT_ENTREPRiSE, UserRole.CLIENT_PROFESSIONNEL).contains(user.getRole())) {
                  throw new RuntimeException("invalid role");
              }

              if (!Set.of(DemandeLivraisonStatus.En_ATTENTE, DemandeLivraisonStatus.EN_COURS).contains(demandeLivraison.getStatus())) {

                     throw new RuntimeException("invalid demande status");
                  }

                  if(!demandeLivraison.getClient().getId().equals(user.getId())){

                    throw new RuntimeException("vous n'avez pas le droit d'annuler une demande");
                  }

                  demandeLivraison.setStatus(DemandeLivraisonStatus.ANNULER);
                  Livraison livraison=(Livraison) demandeLivraison.getLivraisons();
                  livraison.setStatus(LivraisonStatus.ANNULER);
                  saveDemandeLivraison(demandeLivraison);

            }      


            public void deleteDemande(Long id){

              if (!demandeLivraisonRepository.existsById(id)){
                throw new NotFoundException("demande non trouve");
              }
              demandeLivraisonRepository.deleteById(id);
              
            }

            @Override
            public DemandeLivraison getById(Long id) {

               return  demandeLivraisonRepository.findById(id)
                                                 .orElseThrow(() -> new EntityNotFoundException("Demande de livraison non trouvée avec id : " + id));

              }

          @Override
          public List<DemandeLivraison> getByUserId(Long userId) {

            List<DemandeLivraison> demandes= demandeLivraisonRepository.findByClientId(userId);
                 
            if(demandes == null || demandes.isEmpty()){

              throw new NotFoundException("aucune demande lie a cet utlisateur");
             }
             return demandes;
}
  

            @Override
            public List<DemandeLivraison> getAllDemandeLivraison() {
               
              return  demandeLivraisonRepository.findAll();
               
             
              }

          
       

           

        }
        

     


     

     

                

              
              
              
        
    
 
        

    

      



