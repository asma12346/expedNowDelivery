package com.example.demo.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.example.demo.ModelDomain.Colis;

import com.example.demo.ModelDTO.ColisDTO;


@Mapper(componentModel = "spring")

public interface ColisMapperr 

{

     ColisDTO toDto(Colis entity);


     @Mapping(target = "id" , ignore = true) 
     @Mapping(target = "demandeDeLivraison" , ignore = true)
     Colis toEntity(ColisDTO dto);


     @Mapping(target = "id" , ignore = true) 
     @Mapping(target = "demandeDeLivraison" , ignore = true)
     List<Colis> toListEntity(List<ColisDTO> dtoList);


}
