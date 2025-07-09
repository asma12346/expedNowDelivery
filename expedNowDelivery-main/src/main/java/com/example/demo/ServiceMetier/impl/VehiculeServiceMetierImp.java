package com.example.demo.ServiceMetier.impl;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.demo.ServiceMetier.*;

import com.example.demo.ModelDomain.Vehicule;
import com.example.demo.ServiceMetier.VehiculeServiceMetier;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.LivreurDejaAssignerVehiculeException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.VehiculeAlreadyExistException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehiculeRepository;
import com.example.demo.ModelDomain.User;
import com.example.demo.ModelDomain.UserRole;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import com.example.demo.ServiceMetier.UserMetierService;

@Service
@Getter
@Setter

public class VehiculeServiceMetierImp  implements VehiculeServiceMetier{

    private final VehiculeRepository vehiculeRepository;
    private final UserMetierService userMetierService;
    private final UserRepository userRepository;


    public VehiculeServiceMetierImp(VehiculeRepository vehiculeRepository, UserMetierService userMetierService , UserRepository userRepository){
      
        this.vehiculeRepository = vehiculeRepository;
        this.userMetierService = userMetierService;
        this.userRepository = userRepository;
    
    }


   

    @Override
    public void deleteVehicule(Long id) {

      Optional<Vehicule> optionalVehicule = vehiculeRepository.findById(id);
      //le orelsethrow retoune l objet ou declanche une exception

       Vehicule vehicule = optionalVehicule.orElseThrow(() -> new NotFoundException("Véhicule avec id " + id + " introuvable"));
       

            vehiculeRepository.delete(vehicule);
        }
    

    @Override
    public Vehicule saveVoiture(Vehicule vehicule) {

        List<String> conflits = new ArrayList<>();

        if(vehiculeRepository.existsByMatricule(vehicule.getMatricule())){

            conflits.add("matricule");
        }

      if(vehiculeRepository.existsByNumSerie(vehicule.getNumSerie())){

           conflits.add("NumSerie");
        }

        if(!conflits.isEmpty()){

            throw new VehiculeAlreadyExistException(conflits);
        }

        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule updateVehicule(Long vehiculeId, Vehicule vehiculeUpdated) {
        Vehicule vehiculeExisting = vehiculeRepository.findById(vehiculeId).orElseThrow(() -> new NotFoundException("vehicule " + vehiculeId + "introuvable"));

        vehiculeExisting.setMatricule(vehiculeUpdated.getMatricule());
        vehiculeExisting.setNumSerie(vehiculeUpdated.getNumSerie());



        return vehiculeRepository.save(vehiculeExisting);
         
    }


    @Override
    public List<Vehicule> getAll() {
      
        List<Vehicule> ListVec = vehiculeRepository.findAll();
        return (ListVec);
        
    }


    @Override
    public Vehicule getById(Long id) {
        
        return  vehiculeRepository.findById(id).orElseThrow(() -> new NotFoundException("Véhicule avec l'id " + id + " introuvable"));
    }


    @Override
    public Vehicule SearchFirstVehiculeDsiponible(){
    
        return vehiculeRepository.findFirstByDisponibleTrue().orElseThrow(() -> new NotFoundException("aucune vehicule disponible"));

    }

     @Override
     public List<Vehicule> SearchAllVehiculeDsiponible(){

         return vehiculeRepository.findAllByDisponibleTrue();
     }

   
   @Override
    public Vehicule assignerVehicule(Long vehiculeId , long livreurId){

      //recuperer livreur
      User livreur = userRepository.findById(livreurId).orElseThrow(() -> new NotFoundException("livreur introuvable"));

      if (livreur.getRole() != UserRole.LIVREUR_PERMANENT &&  livreur.getRole() != UserRole.LIVREUR_OCCASIONNEL){
         
           throw new BadRequestException("Cet utilisateur n'est pas un livreur");
      }

      //verifie si le livreur a deja un vehicule
      if (livreur.getVehicule() != null){

           throw new BadRequestException("vehcicule deja assigner a ce livreur");          
      }

      //chercher vehicules dispo
     List<Vehicule> vehiculesDispo = SearchAllVehiculeDsiponible();
       
     if (vehiculesDispo.isEmpty()) {

        throw new NotFoundException("Aucun véhicule disponible");
    }
    

     //selectionner l premier vehicule
     Vehicule vehiculedispo = vehiculesDispo.get(0);

      // 5. Mettre à jour les deux côtés de la relation
     vehiculedispo.setLivreur(livreur);
     vehiculedispo.setDisponible(false);
     livreur.setVehicule(vehiculedispo);

     //saving
     userRepository.save(livreur);
     vehiculeRepository.save(vehiculedispo);
     
    return vehiculedispo;
    }

}
