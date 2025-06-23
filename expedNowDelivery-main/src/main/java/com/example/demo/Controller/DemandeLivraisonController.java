package com.example.demo.Controller;
import com.example.demo.ServiceApplicatif.DemandeLivraisonServiceApp;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ModelDTO.DemandeLivraisonDTO;
import com.example.demo.ModelDTO.LivraisonDTO;
import com.example.demo.ModelDomain.DemandeLivraison;

@Validated
@RestController
@RequestMapping("/demandes")
public class DemandeLivraisonController {

    private final DemandeLivraisonServiceApp demandeLivraisonServiceApp;

    public DemandeLivraisonController(DemandeLivraisonServiceApp demandeLivraisonServiceApp){
        this.demandeLivraisonServiceApp = demandeLivraisonServiceApp;
    }

    @PostMapping("/savedemande")
    public ResponseEntity<DemandeLivraisonDTO> saveDemandeLivraison(@RequestBody @Valid DemandeLivraisonDTO demandeLivraisonDTO){
          
         DemandeLivraisonDTO savedDemande = demandeLivraisonServiceApp.saveDemandeLivraison(demandeLivraisonDTO);
         return ResponseEntity.status(HttpStatus.CREATED).body(savedDemande);
      
        }


    
    @PutMapping("/{id}")
    public ResponseEntity<DemandeLivraisonDTO> updatedDemande(
        
          @PathVariable Long demandeId,
          @RequestBody  @Valid DemandeLivraisonDTO updatedDemande)
        {

        DemandeLivraisonDTO demande= demandeLivraisonServiceApp.updateDemande(demandeId, updatedDemande);
        return ResponseEntity.ok(demande);
        }

  
    @PutMapping("/{demandeId}/annuler")
    public ResponseEntity<Void> annulerDemandeParClient(
       
       @PathVariable Long demandeId, 
       @RequestParam Long userId) 
    {  
        
    demandeLivraisonServiceApp.annulerDemandeParClient(demandeId, userId);
    return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{demandeId}")
    public ResponseEntity<?> deleteDemande(@PathVariable Long demandeId)
    {
            demandeLivraisonServiceApp.deleteDemande(demandeId);
            return ResponseEntity.noContent().build();  
    }

    
   @GetMapping("/{demandeId}")
   public ResponseEntity<?> getById(@PathVariable Long demandeId)
   {
        
        DemandeLivraisonDTO demande= demandeLivraisonServiceApp.getById(demandeId);
        return ResponseEntity.ok(demande);
        
       
      
   }

   @GetMapping("/{userId}")
   public ResponseEntity<?> getByUserId(@PathVariable Long userId)
   {
    
        List<DemandeLivraisonDTO> demandes=demandeLivraisonServiceApp.getDemandeByUserId(userId);
      
            
            return ResponseEntity.ok(demandes);
        
    
   }


   @GetMapping("/getAllDemande")
   public ResponseEntity<List<DemandeLivraisonDTO>> getAllDemande()
   {
    List<DemandeLivraisonDTO> demandes = demandeLivraisonServiceApp.getAllDemandes();
    return ResponseEntity.ok(demandes);
    
   }

}
