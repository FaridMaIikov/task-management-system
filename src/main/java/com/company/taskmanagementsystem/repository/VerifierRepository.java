package com.company.taskmanagementsystem.repository;


import com.company.taskmanagementsystem.entity.Verifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifierRepository extends JpaRepository<Verifier,String> {
    boolean existsByEmailAndCode(String email,String code);
}
