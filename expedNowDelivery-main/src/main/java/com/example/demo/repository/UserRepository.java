package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.ModelDomain.User;
import com.example.demo.ModelDomain.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User , Long>{

    
    List<User>  findByRoleNot (UserRole role);
    List<User> findAllByRole(UserRole role);
    List<User> findAllByRoleInAndDisponibleTrue(List<UserRole> roles);
    Optional<User> findById(User userId);
    boolean existsByCin(String cin);
    boolean existsByEmail(String email);




}