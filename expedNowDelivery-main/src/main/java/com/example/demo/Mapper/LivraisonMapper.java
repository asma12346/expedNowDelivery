package com.example.demo.Mapper;

import org.mapstruct.Mapper;


import com.example.demo.ModelDTO.LivraisonDTO;
import com.example.demo.ModelDTO.LivraisonEnCoursDTO;
import com.example.demo.ModelDomain.Livraison;


@Mapper(componentModel = "spring")

public interface LivraisonMapper 

{
    
    LivraisonDTO toDto(Livraison entity);
    Livraison toEntity (LivraisonDTO dto);
    Livraison toEntityLiv(LivraisonEnCoursDTO livraisonEnCoursDTO);


   


}
