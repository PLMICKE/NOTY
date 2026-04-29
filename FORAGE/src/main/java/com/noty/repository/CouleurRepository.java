package com.noty.repository;

import com.noty.model.Couleur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouleurRepository extends JpaRepository<Couleur, Integer> {
}