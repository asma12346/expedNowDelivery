package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ServiceApplicatif.LivraisonServiceApplicatif;
import com.example.demo.ServiceMetier.impl.CauseAnnulationLivreur;

@RestController
@RequestMapping("/livraisons")

public class LivraisonController {

    private final LivraisonServiceApplicatif livraisonServiceApplicatif;

    public LivraisonController(LivraisonServiceApplicatif livraisonServiceApplicatif){
        this.livraisonServiceApplicatif=livraisonServiceApplicatif;
    }

@PutMapping("/{livraisonId}/assigner")
public ResponseEntity<Void> assignerLivreurProcheEtChangerStatut(@PathVariable Long livraisonId)  
        
        {
            livraisonServiceApplicatif.assignerLivreurEtChangerStatut(livraisonId);
            return ResponseEntity.ok().build();
            
        }

        
@PutMapping("/{livraisonId}/annuler/")
public ResponseEntity<Void> annulerLivraisonParLivreur(
     
       @PathVariable Long livraisonId,
       @RequestParam Long userId,
       @RequestParam  CauseAnnulationLivreur cause
)
{
    livraisonServiceApplicatif.annulerLivraisonParLivreur(livraisonId, userId,cause);
    return ResponseEntity.ok().build();
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