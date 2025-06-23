package com.example.demo.exception;

import java.util.List;

public class UserAlreadyExistsException  extends RuntimeException{

    List<String> fields;

public UserAlreadyExistsException(List<String> fields)
    {
        this.fields=fields;
    }    

}
