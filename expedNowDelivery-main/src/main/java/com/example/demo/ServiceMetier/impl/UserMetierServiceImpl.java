package com.example.demo.ServiceMetier.impl;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.lang.RuntimeException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.ModelDomain.User;
import com.example.demo.ModelDomain.UserRole;
import com.example.demo.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import com.example.demo.ServiceMetier.*;


import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class UserMetierServiceImpl  implements UserMetierService{


private final UserRepository userRepository ;
private final PasswordEncoder passwordEncoder;

public UserMetierServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {

    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    

}


public User saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
}

//sauvgarde admin
public User saveAdmin(User user){
    user.setRole(UserRole.ADMIN);
    return saveUser(user);
}

public User saveLivreurpermenant(User user){
    user.setRole(UserRole.LIVREUR_PERMANENT);
    return saveUser(user);
}

public User saveLivreurOccasionnel(User user){
    user.setRole(UserRole.LIVREUR_OCCASIONNEL);
    return saveUser(user);
}

public User saveClientPro(User user) {
        user.setRole(UserRole.CLIENT_PROFESSIONNEL);
        return saveUser(user);
    }

 public User saveClientEntrp(User user) {
        user.setRole(UserRole.CLIENT_ENTREPRiSE);
        return saveUser(user);
    }

public User getUserById(Long id) {
return userRepository.findById(id)
.orElseThrow(() -> new RuntimeException("user avec ID " + id + " non trouvé"));
}

public List<User> getAllUserByRole(UserRole role){
    return userRepository.findAllByRoleIn(role);
   }

 

public void desactiveUser(Long id){

    Optional<User> userOpt = userRepository.findById(id);
    if (userOpt.isPresent()){
        User user =  userOpt.get();
        user.setActive(false); 
        userRepository.save(user);
    } else {
        throw new RuntimeException("user not found");
    }


}

public User activateUser(Long id) {

    User user = getUserById(id);
    if (user.isActive()) {

        throw new IllegalStateException("L'utilisateur est déjà actif");
    }
    user.setActive(true); // Réactivation
    return userRepository.save(user);

}

 public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id));

        // Mise à jour des champs
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setPassword(updatedUser.getPassword()); // encoder si nécessaire
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setActive(updatedUser.isActive());

        return userRepository.save(existingUser);
    }


public List<User>  getLivreursdispos(){

        return userRepository.findAllByRoleInDisponibleTrue(
                List.of(UserRole.LIVREUR_OCCASIONNEL, UserRole.LIVREUR_PERMANENT)
            );                                  
}


private double calculerDistance(double lat1, double lon1, double lat2, double lon2) {
    final int R = 6371; // Rayon de la Terre en km

    double latDistance = Math.toRadians(lat2 - lat1);
    double lonDistance = Math.toRadians(lon2 - lon1);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return R * c;
}
   public Optional<User> getLivreurDispoEtProche(double latitudeDemande ,double longitudeDemande ) {
              
        List<User> livreursDispo =getLivreursdispos();
         //vide
        if (livreursDispo.isEmpty()){
          return Optional.empty();
        }

        User userplusProche = livreursDispo.stream().min(Comparator.comparingDouble(livreur -> calculerDistance(livreur.getLatitude(), livreur.getLongitude() ,latitudeDemande, longitudeDemande))).orElse(null);
   
        return Optional.ofNullable(userplusProche);
       }

    }







