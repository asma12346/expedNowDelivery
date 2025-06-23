package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.ModelDomain.Vehicule;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long>{
    

    Optional<Vehicule> findFirstByDisponibleTrue();
    boolean existsByMatricule(String matricule);
    boolean existsByNumSerie(String numSerie);
    List<Vehicule> findAllByDisponibleTrue();
    

}
