package com.example.demo.ServiceMetier;

import com.example.demo.ModelDomain.DemandeLivraison;
import com.example.demo.ModelDomain.Livraison;
import com.example.demo.ModelDomain.LivraisonStatus;
import com.example.demo.ModelDomain.User;
import com.example.demo.ServiceMetier.impl.CauseAnnulationLivreur;

public interface LivraisonServiceMetier {
void livraisonAchever(Long livraisonId , Long userId);
void annulerLivraisonParLivreur(Long livraisonId,Long userId, CauseAnnulationLivreur cause);
void  livraisonEnCours(Long livraisonId , Long userId,Livraison livraison);
}
