package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ModelDTO.LivraisonEnCoursDTO;
import com.example.demo.ServiceApplicatif.LivraisonServiceApplicatif;
import com.example.demo.ServiceMetier.impl.CauseAnnulationLivreur;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/livraisons")

public class LivraisonController {

    private final LivraisonServiceApplicatif livraisonServiceApplicatif;

    public LivraisonController(LivraisonServiceApplicatif livraisonServiceApplicatif){
        this.livraisonServiceApplicatif=livraisonServiceApplicatif;
    }


@PutMapping("/{livraisonid}/encours")
public ResponseEntity<?> livraisonEncours(

       @PathVariable Long livraisonId,
       @RequestParam Long userId,
       @RequestBody LivraisonEnCoursDTO livraisonEnCoursDTO

)
{
     livraisonServiceApplicatif.livraisonEnCours(livraisonId, userId,livraisonEnCoursDTO);
     String message = " livraison en cours";
     return ResponseEntity.ok(message);
}

        
@PutMapping("/{livraisonId}/annuler/")
public ResponseEntity<?> annulerLivraisonParLivreur(
     
       @PathVariable Long livraisonId,
       @RequestParam Long userId,
       @RequestParam  CauseAnnulationLivreur cause
)
{
    livraisonServiceApplicatif.annulerLivraisonParLivreur(livraisonId, userId,cause);
        
   String message = "La livraison a été annulée pour la raison suivante : " + cause.name().replace('_', ' ').toLowerCase();

    return ResponseEntity.ok(message);
}

@PutMapping("/{livraisonId}/achever")
public ResponseEntity<Void> livraisonAchever(
    
       @PathVariable Long livraisonId,
       @RequestParam  Long userId
)
{
    livraisonServiceApplicatif.livraisonAchever(livraisonId, userId);
    return ResponseEntity.ok().build();
}

}