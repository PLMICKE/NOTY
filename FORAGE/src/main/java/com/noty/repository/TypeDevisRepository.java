package com.noty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.noty.model.TypeDevis;

@Repository
public interface TypeDevisRepository extends JpaRepository<TypeDevis, Integer> {
    // @Query("SELECT t FROM TypeDevis t WHERE t.id = :currentId + 1")
    // Optional<TypeDevis> findNextTypeDevis(@Param("currentId") int currentId);
}