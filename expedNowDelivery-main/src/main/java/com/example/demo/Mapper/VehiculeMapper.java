package com.example.demo.Mapper;


import org.springframework.stereotype.Component;

import com.example.demo.ModelDTO.VehiculeDTO;
import com.example.demo.ModelDomain.Vehicule;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.ModelDTO.SaveVehiculeRequestDTO;
import com.example.demo.ModelDTO.UpdatevehiculeRequestDTO;

@Mapper(componentModel = "spring")
public interface VehiculeMapper

{

   VehiculeDTO toDto(Vehicule vehicule);
   

            List<VehiculeDTO> toDtoList(List<Vehicule> vehicules);

            Vehicule toEntity(VehiculeDTO vehiculeDTO);

            Vehicule toEntity(UpdatevehiculeRequestDTO updatedVehiculeDTO);
        
            Vehicule toEntity(SaveVehiculeRequestDTO saveVehiculeRequestDTO);

}