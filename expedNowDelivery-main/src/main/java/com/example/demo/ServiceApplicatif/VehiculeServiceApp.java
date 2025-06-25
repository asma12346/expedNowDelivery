package com.example.demo.ServiceApplicatif;
import java.util.List;

import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import com.example.demo.Mapper.VehiculeMapper;
import com.example.demo.ModelDTO.VehiculeDTO;
import com.example.demo.ModelDomain.Vehicule;
import com.example.demo.ServiceMetier.VehiculeServiceMetier;
import com.example.demo.ModelDTO.SaveVehiculeRequestDTO;
import com.example.demo.ModelDTO.UpdatevehiculeRequestDTO;


@Service
public class VehiculeServiceApp {

    private final VehiculeMapper vehiculeMapper;
    private final VehiculeServiceMetier vehiculeServiceMetier;


    public VehiculeServiceApp(VehiculeMapper vehiculeMapper, VehiculeServiceMetier vehiculeServiceMetier){

        this.vehiculeMapper = vehiculeMapper;
        this.vehiculeServiceMetier = vehiculeServiceMetier;
    }


    public  VehiculeDTO saveVoiture(SaveVehiculeRequestDTO saveVehiculeRequestDTO){

        Vehicule vehicule = vehiculeMapper.toEntity(saveVehiculeRequestDTO);
         System.out.println("Matricule: " + vehicule.getMatricule());
        System.out.println("NumSerie: " + vehicule.getNumSerie());
        Vehicule vehiculeSaved = vehiculeServiceMetier.saveVoiture(vehicule);
        return vehiculeMapper.toDto(vehiculeSaved);

    }

    public void deleteVehicule(Long id){

         vehiculeServiceMetier.deleteVehicule(id);
    }


    public VehiculeDTO updateVehicule(Long vehiculeId,  UpdatevehiculeRequestDTO vehiculeUpdatedDTO){

        Vehicule vehicule = vehiculeMapper.toEntity(vehiculeUpdatedDTO);
        Vehicule vehiculeUpdated = vehiculeServiceMetier.updateVehicule(vehiculeId, vehicule);
        return vehiculeMapper.toDto(vehiculeUpdated);
    }

    public VehiculeDTO getById(Long id){

        Vehicule vehicule = vehiculeServiceMetier.getById(id);
        return vehiculeMapper.toDto(vehicule);
        

    }


    public List<VehiculeDTO> getAll() {

        List<Vehicule> vehicules = vehiculeServiceMetier.getAll();
        return vehiculeMapper.toDtoList(vehicules);


    }

    public List<VehiculeDTO> getAllVehiculeDispo(){
       
        List<Vehicule> vehicules = vehiculeServiceMetier.SearchAllVehiculeDsiponible();
        return vehiculeMapper.toDtoList(vehicules);
    }


      public VehiculeDTO assignerVehicule(Long vehiculeId,long livreurId){
       
        Vehicule vehicule=   vehiculeServiceMetier.assignerVehicule(vehiculeId, livreurId);
        return vehiculeMapper.toDto(vehicule);

      }

}
