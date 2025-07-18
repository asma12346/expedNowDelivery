package com.example.demo.ModelDTO;

import com.example.demo.ModelDomain.LivraisonStatus;

import jakarta.validation.constraints.NotNull;

public class LivraisonEnCoursDTO {


      @NotNull
    private LivraisonStatus status;
      
}
