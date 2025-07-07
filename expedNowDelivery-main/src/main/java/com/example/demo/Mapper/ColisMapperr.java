package com.example.demo.Mapper;

import org.mapstruct.Mapper;

import com.example.demo.ModelDomain.Colis;

import com.example.demo.ModelDTO.ColisDTO;


@Mapper(componentModel = "spring")

public interface ColisMapperr {



     ColisDTO toDto(Colis entity);

     Colis toEntity (ColisDTO dto);

}
