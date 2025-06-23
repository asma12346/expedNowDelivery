package com.example.demo.Controller;

import com.example.demo.ServiceApplicatif.UserServiceApp;
import com.example.demo.ModelDomain.UserRole;
import com.example.demo.ModelDTO.UserDTO;
import com.example.demo.ModelDomain.User;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping("/")
public class UserController {

    private final UserServiceApp userServiceApplicatif;

        public UserController(UserServiceApp userServiceApplicatif){
        this.userServiceApplicatif = userServiceApplicatif;
        }

    @PostMapping("/admin")
    public  ResponseEntity<UserDTO> saveAdmin(@RequestBody UserDTO userDTO){
        
        userDTO.setRole(UserRole.ADMIN);
        
        UserDTO saved= userServiceApplicatif.saveUser(userDTO);
       
       return ResponseEntity.status(HttpStatus.CREATED).body(saved);


    }

    @PostMapping("/livreurpermemnant")
    public ResponseEntity<UserDTO> saveLivreurpermenant(@RequestBody UserDTO userDTO){

        userDTO.setRole(UserRole.LIVREUR_PERMANENT);

        UserDTO saved =userServiceApplicatif.saveUser(userDTO);
         
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
                        
    }


    @PostMapping("/livreuroccasionnel")

    public ResponseEntity<UserDTO> saveLivreuroccasionnel(@RequestBody UserDTO userDTO){

        userDTO.setRole(UserRole.LIVREUR_OCCASIONNEL);

        UserDTO saved =userServiceApplicatif.saveUser(userDTO);
         
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
                        
    }
     

    
    @PostMapping("/clientEntreprise")

    public ResponseEntity<UserDTO> saveClientEntreprise(@RequestBody UserDTO userDTO){

        userDTO.setRole(UserRole.CLIENT_ENTREPRiSE);

        UserDTO saved =userServiceApplicatif.saveUser(userDTO);
         
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
                        
    }
     

    @PostMapping("/clientProfessionnel")

    public ResponseEntity<UserDTO> saveLivreurProfessionnel(@RequestBody UserDTO userDTO){

        userDTO.setRole(UserRole.CLIENT_PROFESSIONNEL);

        UserDTO saved =userServiceApplicatif.saveUser(userDTO);
         
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
                        
    }
     
@GetMapping("/{userId}")
public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
   try {
    
        UserDTO userDto= userServiceApplicatif.getUserById(userId);
        return ResponseEntity.ok(userDto);
   
    } catch (RuntimeException e) {

       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
   }

   
}

@GetMapping("/{role}/getAll")
public ResponseEntity<List<UserDTO>> getAllUserByRole(@PathVariable UserRole role){
  
     List<UserDTO> users = userServiceApplicatif.getAllUserByRole(role);

    return ResponseEntity.ok(users);
  
}

@PutMapping("/{userId}/desactive")
public ResponseEntity<Void> desactiveUser(@PathVariable Long userId){

     userServiceApplicatif.desactiveUser(userId);

     return ResponseEntity.noContent().build();

}

@PutMapping("/{userId}/activate")
public ResponseEntity<UserDTO> activateUser(@PathVariable Long userId){

    UserDTO activated= userServiceApplicatif.activateUser(userId);

    return ResponseEntity.ok(activated);
}

@PutMapping("/{userId}/update")
public ResponseEntity<UserDTO> updateUser(
    
      @PathVariable Long userId,
      @RequestParam UserDTO userUpdated
)

{
    UserDTO updated = userServiceApplicatif.updateUser(userId, userUpdated) ;
    return ResponseEntity.ok(updated);
      
}


}



