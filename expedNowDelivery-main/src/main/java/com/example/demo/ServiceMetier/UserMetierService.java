package com.example.demo.ServiceMetier;


import com.example.demo.ModelDTO.UserDTO;
import com.example.demo.ModelDomain.User;
import com.example.demo.ModelDomain.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserMetierService {


    User saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUserByRole(UserRole role);
    void desactiveUser(Long id);
    User activateUser(Long id) ;
    User updateUser(Long id, User updatedUser);
    Optional<User> getLivreurDispoEtProche(double latitudeDemande ,double longitudeDemande );


}