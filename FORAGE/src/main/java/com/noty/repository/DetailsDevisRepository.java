package com.noty.repository;

import com.noty.model.DetailsDevis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsDevisRepository extends JpaRepository<DetailsDevis, Integer> {
    List<DetailsDevis> findByDevisId(int devisId);
}
