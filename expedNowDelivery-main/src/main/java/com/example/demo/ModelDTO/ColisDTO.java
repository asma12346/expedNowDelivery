package com.example.demo.ModelDTO;
import com.example.demo.ModelDomain.FragiliteColis;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@AllArgsConstructor
public class ColisDTO {
 
    private String description;

   
    private FragiliteColis fragiliteColis;
    
    
}
