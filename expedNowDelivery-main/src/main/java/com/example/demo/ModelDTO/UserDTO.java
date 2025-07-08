package com.example.demo.ModelDTO;

import com.example.demo.ModelDomain.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
 public class UserDTO {
    private Long id;

    @NotBlank(message = "Le nom complet est obligatoire.")
    private String firstName;
    
    @NotBlank(message = "Le nom complet est obligatoire.")
    private String lastName;

    @NotBlank(message = "L'adresse est obligatoire.")
     private String address;

    @Email
    private String email;
    
    private String phoneNumber;
    private UserRole role;

     @NotNull(message = "disponible est obligatoire.")
    private Boolean disponible;
    
    @NotBlank(message = "carte CIN est obligatoire")
    private String cin;
    
    private boolean active;
    private double latitude;
    private double longitude;
}



