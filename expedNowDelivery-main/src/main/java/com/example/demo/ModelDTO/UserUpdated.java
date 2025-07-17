package com.example.demo.ModelDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
public class UserUpdated {

   @NotBlank(message = "Le nom complet est obligatoire.")
    private String firstName;
    
    @NotBlank(message = "Le nom complet est obligatoire.")
    private String lastName;

    @NotBlank(message = "L'adresse est obligatoire.")
     private String address;

    @NotBlank(message = "phone number est obligatoire.")
    private String phoneNumber;

     private double latitude;

     private double longitude;

    
    
}

