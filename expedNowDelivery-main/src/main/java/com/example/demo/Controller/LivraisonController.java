package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ServiceApplicatif.LivraisonServiceApplicatif;

@RestController
@RequestMapping("/")

public class LivraisonController {

    private final LivraisonServiceApplicatif livraisonServiceApplicatif;

    public LivraisonController(LivraisonServiceApplicatif livraisonServiceApplicatif){
        this.livraisonServiceApplicatif=livraisonServiceApplicatif;
    }

@PutMapping("{/livraisonId}/changerstatut")
public ResponseEntity<Void> assignerLivreurProcheEtChangerStatut(@PathVariable Long livrasionId)  
        
        {
            livraisonServiceApplicatif.assignerLivreurEtChangerStatut(livrasionId);
            return ResponseEntity.ok().build();
            
        }

        
@PutMapping("/{livraisonId}/annuler")
public ResponseEntity<Void> annulerLivraisonParLivreur(
     
       @PathVariable Long livraisonId,
       @RequestParam Long userId
)
{
    livraisonServiceApplicatif.annulerLivraisonParLivreur(livraisonId, userId);
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