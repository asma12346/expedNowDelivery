package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.ModelDomain.User;

import com.example.demo.ModelDomain.DemandeLivraison;

@Repository
public interface DemandeLivraisonRepository  extends JpaRepository<DemandeLivraison, Long>{
    List<DemandeLivraison>findByClientId(Long id);

}
