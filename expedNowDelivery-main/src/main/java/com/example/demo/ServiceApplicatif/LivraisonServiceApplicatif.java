
package com.example.demo.ServiceApplicatif;

import com.example.demo.Mapper.LivraisonMapper;
import com.example.demo.ModelDomain.Livraison;
import com.example.demo.ServiceMetier.LivraisonServiceMetier;
import com.example.demo.ServiceMetier.impl.CauseAnnulationLivreur;

import org.springframework.stereotype.Service;


@Service

public class LivraisonServiceApplicatif {


    private LivraisonMapper livraisonMapper;
    private LivraisonServiceMetier livraisonServiceMetier;


    public LivraisonServiceApplicatif(LivraisonMapper livraisonMapper, LivraisonServiceMetier livraisonServiceMetier){
        this.livraisonMapper =livraisonMapper;
        this.livraisonServiceMetier = livraisonServiceMetier;
    }


    public void assignerLivreurEtChangerStatut(Long livraisonId){

        livraisonServiceMetier.assignerLivreurProcheEtChangerStatut(livraisonId);
    }

   public   void annulerLivraisonParLivreur(Long livraisonId,Long userId, CauseAnnulationLivreur cause){
       livraisonServiceMetier.annulerLivraisonParLivreur(livraisonId, userId,cause);
   }

   public void livraisonAchever(Long livraisonId , Long livreurId){
       livraisonServiceMetier.livraisonAchever(livraisonId, livreurId);
   }

}



