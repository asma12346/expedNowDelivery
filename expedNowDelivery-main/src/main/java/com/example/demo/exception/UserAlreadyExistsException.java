package com.example.demo.exception;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserAlreadyExistsException  extends RuntimeException{

 private final  List<String> fields;

public UserAlreadyExistsException(List<String> fields)
    {
        super("un utilisateur existe deja avec ses champs :"  + String.join(",",fields));
        this.fields=fields;
    }    



    

}
