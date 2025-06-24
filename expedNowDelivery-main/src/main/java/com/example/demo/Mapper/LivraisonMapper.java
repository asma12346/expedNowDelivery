package com.example.demo.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.ModelDTO.LivraisonDTO;
import com.example.demo.ModelDomain.Livraison;


@Mapper(componentModel = "spring")

public interface LivraisonMapper {
    

    LivraisonMapper INSTANCE = Mappers.getMapper(LivraisonMapper.class);

   
    LivraisonDTO toDto(Livraison entity);
    Livraison toEntity (LivraisonDTO dto);

   


}
