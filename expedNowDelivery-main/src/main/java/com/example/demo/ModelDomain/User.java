package com.example.demo.ModelDomain;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.demo.ModelDomain.Livraison;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment id
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable =  false)
    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

   @Enumerated(EnumType.STRING)
    private UserRole role;  

  @OneToMany(mappedBy = "user")
  private List<Notifications> notifications;

  //la relation est gérée ou mappée par l'attribut livreur dans l'entité Vehicule
  @OneToOne(mappedBy = "livreur")
  //Vehicule est la proprietaire
  private Vehicule vehicule;

  @OneToMany(mappedBy = "client")
  @JsonIgnore
  private List<DemandeLivraison> demandesClient;

 @OneToMany(mappedBy = "livreur")
 private List<Livraison> livraisons;

 @Column(name = "numero_cin")
 private String cin;

  
 private double latitude;
private double longitude;

private boolean disponible = true;

private boolean active = true;

     public User(String address,String password, String firstName,String lastName,String cin,double latitude, double longitude, String email, String phoneNumber,List<DemandeLivraison> demandesClient,List<Livraison> livraisons, UserRole role,boolean disponible,boolean active,Notifications notifications,Vehicule vehicule) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.disponible = disponible;
        this.latitude=latitude;
        this.longitude = longitude;
        this.vehicule =vehicule;
        this.livraisons =livraisons;
        this.address = address;
        this.disponible=disponible;
        this.active = active;
        this.demandesClient=demandesClient;
        this.cin= cin;
    }


  

}
