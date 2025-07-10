package com.example.demo.ModelDomain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Colis {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;

private String description;


private FragiliteColis fragiliteColis;

@ManyToOne
@JoinColumn(name = "demandeDeLivrasion_id")
@JsonIgnore
private DemandeLivraison demandeDeLivraison;


 public Colis(String description,FragiliteColis fragiliteColis, DemandeLivraison demandeDeLivraison) {
        this.description = description;
        this.fragiliteColis = fragiliteColis;
        this.demandeDeLivraison = demandeDeLivraison;
    }







}