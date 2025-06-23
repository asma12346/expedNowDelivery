package com.example.demo.ServiceApplicatif;

import com.example.demo.Mapper.LivraisonMapper;
import com.example.demo.ServiceMetier.LivraisonServiceMetier;

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

   public   void annulerLivraisonParLivreur(Long livraisonId,Long userId){
       livraisonServiceMetier.annulerLivraisonParLivreur(livraisonId, userId);
   }

   public void livraisonAchever(Long livraisonId , Long livreurId){
       livraisonServiceMetier.livraisonAchever(livraisonId, livreurId);
   }

}
