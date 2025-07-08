package com.example.demo.ServiceApplicatif;


import com.example.demo.ModelDomain.User;
import com.example.demo.ModelDomain.UserRole;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.ModelDTO.UserDTO;
import com.example.demo.ModelDTO.UserSaveDTO;
import com.example.demo.ServiceMetier.UserMetierService;
import com.example.demo.ModelDTO.UserUpdated;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceApp {

    final private UserMapper userMapper;
    final private UserMetierService userMetierService;
    final private PasswordEncoder passwordEncoder;


    public UserServiceApp(UserMapper userMapper, UserMetierService userMetierService, PasswordEncoder passwordEncoder){
        this.userMapper= userMapper;
        this.userMetierService= userMetierService;
        this.passwordEncoder= passwordEncoder;
    }

    
    //Méthode pour enregistrer un user avec un mot de passe encodé
     public UserDTO saveUser(UserSaveDTO userDTO){
        //convertir en entity 

        User  user= userMapper.toEntity(userDTO);
      //sauvgarde dans la base 
        User usersaved = userMetierService.saveUser(user);
       
        return userMapper.toDto(usersaved);

    }





public UserDTO getUserById(Long id) {

       User user = userMetierService.getUserById(id);
       return  userMapper.toDto(user);
           
    }

public List<UserDTO> getAllUserByRole(UserRole role){

        List<User> users = userMetierService.getAllUserByRole(role);
        return userMapper.toDtoList(users);

       }
    
  
 public void desactiveUser(Long id){
        
        userMetierService.desactiveUser(id);

       }


public UserDTO activateUser(Long id) {
     
    User user= userMetierService.activateUser(id);
    return userMapper.toDto(user);
    
}


public UserDTO updateUser(Long id, UserUpdated userUpdatedd){
    
      User user = userMapper.toEntity(userUpdatedd);
      User userupdated = userMetierService.updateUser(id, user);
      return userMapper.toDto(userupdated);
}


 Optional<UserDTO> getLivreurDispoEtProche(double latitudeDemande ,double longitudeDemande ){
      return userMetierService.getLivreurDispoEtProche(latitudeDemande, longitudeDemande).map(userMapper::toDto);
 }
}
    
