package com.example.demo.ServiceMetier;


import com.example.demo.ModelDTO.UserDTO;
import com.example.demo.ModelDomain.User;
import com.example.demo.ModelDomain.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserMetierService {


    User saveUser(User user);
    User saveAdmin(User user);
    User saveLivreurpermenant(User user);
    User saveLivreurOccasionnel(User user);
    User saveClientPro(User user);
    User saveClientEntrp(User user);
    User getUserById(Long id);
    List<User> getAllUserByRole(List<UserRole> role);
    void desactiveUser(Long id);
    User activateUser(Long id) ;
    User updateUser(Long id, User updatedUser);
    Optional<User> getLivreurDispoEtProche(double latitudeDemande ,double longitudeDemande );


}