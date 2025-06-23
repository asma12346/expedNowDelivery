package com.example.demo.exception;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VehiculeAlreadyExistException extends RuntimeException {

    List <String> fields;

    public VehiculeAlreadyExistException(List <String> fields)
    {
        super("Le véhicule existe déjà avec les champs suivants : " +String.join(",",fields));
        this.fields= fields;
    }    

}
