
package com.example.demo.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.ModelDomain.DemandeLivraison;
import com.example.demo.ModelDTO.DemandeLivraisonDTO;


@Mapper(componentModel = "spring")
public interface DemandeLivraisonMapper 

{

    
    DemandeLivraisonDTO toDto(DemandeLivraison demandeLivraison);

    DemandeLivraison toEntity (DemandeLivraisonDTO demandeLivraisonDTO);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "En_ATTENTE")
    @Mapping(target = "datecreationdemande", ignore = true)
    @Mapping(target = "livraisons", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    DemandeLivraison demandeLivraisonDtoToDemandeLivraison (DemandeLivraisonDTO demandeLivraisonDTO);


    List<DemandeLivraisonDTO> toDtoList(List<DemandeLivraison> demandes);




   
}
