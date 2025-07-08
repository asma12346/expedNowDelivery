package com.example.demo.Controller;

import com.example.demo.ServiceApplicatif.UserServiceApp;
import com.example.demo.ModelDomain.UserRole;
import com.example.demo.ModelDTO.UserDTO;
import com.example.demo.ModelDTO.UserSaveDTO;
import com.example.demo.ModelDTO.UserUpdated;

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



@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceApp userServiceApplicatif;

        public UserController(UserServiceApp userServiceApplicatif){
        this.userServiceApplicatif = userServiceApplicatif;
        }

    @PostMapping("/")
    public  ResponseEntity<UserDTO> saveUser(@RequestBody UserSaveDTO userDTO){
                
        UserDTO saved= userServiceApplicatif.saveUser(userDTO);
       
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
      @RequestBody UserUpdated userUpdated
)

{
    UserDTO updated = userServiceApplicatif.updateUser(userId, userUpdated) ;
    return ResponseEntity.ok(updated);
      
}


}



