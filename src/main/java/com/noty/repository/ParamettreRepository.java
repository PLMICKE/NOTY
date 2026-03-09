package com.noty.repository;

import com.noty.model.Paramettre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface ParamettreRepository extends JpaRepository<Paramettre, Integer> {
    List<Paramettre> findByMatiereId(int matiereId);
}