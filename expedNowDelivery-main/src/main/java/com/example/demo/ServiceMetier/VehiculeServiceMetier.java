package com.example.demo.ServiceMetier;
import java.util.List;

import com.example.demo.ModelDomain.Vehicule;

public interface VehiculeServiceMetier {

    public Vehicule saveVoiture(Vehicule vehicule);
    public void deleteVehicule(Long id);
    public Vehicule updateVehicule(Long vehiculeId, Vehicule vehiculeUpdated);
    public Vehicule SearchFirstVehiculeDsiponible();
    public List<Vehicule> SearchAllVehiculeDsiponible();
    public Vehicule assignerVehicule(Long vehiculeId,long livreurId);
    public Vehicule getById(Long id);
    public List<Vehicule> getAll();

}
