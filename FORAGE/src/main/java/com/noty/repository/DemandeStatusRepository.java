package com.noty.repository;

import com.noty.model.DemandeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeStatusRepository extends JpaRepository<DemandeStatus, Integer> {
}
