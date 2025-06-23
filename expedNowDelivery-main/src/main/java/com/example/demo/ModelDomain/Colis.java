package com.example.demo.ModelDomain;
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

private long id;
private String description;

private String destination;

private String adresseDépart;

private FragiliteColis fragiliteColis;

@ManyToOne
@JoinColumn(name = "demandeDeLivrasion_id")
private DemandeLivraison demandeDeLivraison;








}