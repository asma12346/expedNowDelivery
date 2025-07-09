
package com.example.demo.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.example.demo.ModelDomain.DemandeLivraison;
import com.example.demo.ModelDTO.DemandeLivraisonDTO;
import com.example.demo.ModelDTO.SaveDemandeRequestDTO;


@Mapper(componentModel = "spring")
public interface DemandeLivraisonMapper {

    
    DemandeLivraisonDTO toDto(DemandeLivraison demandeLivraison);
    DemandeLivraison toEntity (DemandeLivraisonDTO demandeLivraisonDTO);
    List<DemandeLivraisonDTO> toDtoList(List<DemandeLivraison> demandes);
    DemandeLivraison toEntity(SaveDemandeRequestDTO saveDemandeRequestDTO);


   
}
