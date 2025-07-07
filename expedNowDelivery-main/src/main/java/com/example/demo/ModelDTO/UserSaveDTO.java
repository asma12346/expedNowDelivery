package com.example.demo.ModelDTO;

import com.example.demo.ModelDomain.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
public class UserSaveDTO {

    private Long id;

    @NotBlank(message = "Le nom complet est obligatoire.")
    private String firstName;
    
    @NotBlank(message = "Le nom complet est obligatoire.")
    private String lastName;

    @NotBlank(message = "L'adresse est obligatoire.")
     private String adress;

    @Email
    private String email;
    
    @NotBlank(message = "password obbligatoire")
    private String password;

    @NotBlank(message = "phone number est obligatoire.")
    private String phoneNumber;
    
    private UserRole role;

    
    
}
