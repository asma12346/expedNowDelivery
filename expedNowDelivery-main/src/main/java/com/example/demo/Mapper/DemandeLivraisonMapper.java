
package com.example.demo.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.ModelDomain.DemandeLivraison;
import com.example.demo.ModelDTO.DemandeLivraisonDTO;
import com.example.demo.Mapper.ColisMapperr;
import com.example.demo.Mapper.LivraisonMapper;

@Mapper(componentModel = "spring")
public interface DemandeLivraisonMapper {

    DemandeLivraisonMapper INSTANCE = Mappers.getMapper(DemandeLivraisonMapper.class);

    
    DemandeLivraisonDTO toDto(DemandeLivraison demandeLivraison);
    DemandeLivraison toEntity (DemandeLivraisonDTO demandeLivraisonDTO);
    List<DemandeLivraisonDTO> toDtoList(List<DemandeLivraison> demandes);


   
}
