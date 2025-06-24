package com.example.demo.ServiceApplicatif;


import com.example.demo.Mapper.DemandeLivraisonMapper;
import com.example.demo.Mapper.LivraisonMapper;
import com.example.demo.ModelDomain.DemandeLivraison;
import com.example.demo.ModelDomain.Livraison;
import com.example.demo.ServiceMetier.DemandeLivraisonServiceMetier;
import com.example.demo.ModelDTO.DemandeLivraisonDTO;
import com.example.demo.ModelDTO.LivraisonDTO;
import com.example.demo.ModelDTO.UserDTO;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.ModelDomain.User;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DemandeLivraisonServiceApp {

    private final DemandeLivraisonServiceMetier demandeLivraisonServiceMetier;
    private final DemandeLivraisonMapper demandeLivraisonMapper;
    private final UserMapper userMapper;

    public DemandeLivraisonServiceApp(DemandeLivraisonServiceMetier demandeLivraisonServiceMetier,UserMapper userMapper, DemandeLivraisonMapper demandeLivraisonMapper){
        this.demandeLivraisonServiceMetier = demandeLivraisonServiceMetier;
        this.demandeLivraisonMapper = demandeLivraisonMapper;
        this.userMapper = userMapper;
    }

     public DemandeLivraisonDTO saveDemandeLivraison(DemandeLivraisonDTO demande)
     {
        //convertir en entity
         DemandeLivraison demandeLivraison = demandeLivraisonMapper.toEntity(demande);
         DemandeLivraison saved = demandeLivraisonServiceMetier.saveDemandeLivraison(demandeLivraison);
         return demandeLivraisonMapper.toDto(saved);
     }
         
 
     public DemandeLivraisonDTO updateDemande (long id,DemandeLivraisonDTO demandeLivraisonDTO){

        DemandeLivraison demandeLivraison = demandeLivraisonMapper.toEntity(demandeLivraisonDTO);
        DemandeLivraison updated = demandeLivraisonServiceMetier.updateDemande(id, demandeLivraison);
        return demandeLivraisonMapper.toDto(updated);
     }

      public void annulerDemandeParClient(Long demandeId, long userId) {
         
        demandeLivraisonServiceMetier.annulerDemandeParClient(demandeId, userId);
       
    }

      public void deleteDemande(Long id){

         demandeLivraisonServiceMetier.deleteDemande(id);
              
            }
    
    public DemandeLivraisonDTO getById(Long id){

        DemandeLivraison demande= demandeLivraisonServiceMetier.getById(id);
        return  demandeLivraisonMapper.toDto(demande);
    }



    public List<DemandeLivraisonDTO> getDemandeByUserId (Long userId){

        List<DemandeLivraison> demandes= demandeLivraisonServiceMetier.getByUserId(userId);
        return demandeLivraisonMapper.toDtoList(demandes);
         
    }

    public List<DemandeLivraisonDTO> getAllDemandes() {

        List<DemandeLivraison> demandes = demandeLivraisonServiceMetier.getAllDemandeLivraison();
          return demandeLivraisonMapper.toDtoList(demandes);
    }

}


