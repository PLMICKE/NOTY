package com.noty.repository;

import com.noty.model.Devis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Integer> {
    List<Devis> findByDemandeId(int demandeId);
}
