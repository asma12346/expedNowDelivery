package com.example.demo.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.ModelDomain.Colis;
import com.example.demo.ModelDomain.DemandeLivraison;
import com.example.demo.ModelDomain.Livraison;
import com.example.demo.ModelDTO.ColisDTO;
import com.example.demo.ModelDTO.DemandeLivraisonDTO;
import com.example.demo.ModelDTO.LivraisonDTO;

@Mapper(componentModel = "spring")

public interface ColisMapperr {



     ColisDTO toDto(Colis entity);

     Colis toEntity (ColisDTO dto);

}
