package com.noty.repository;

import com.noty.model.DemandeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeStatusRepository extends JpaRepository<DemandeStatus, Integer> {
    List<DemandeStatus> findByDemandeId(int demandeId);
    List<DemandeStatus> findByDemandeIdOrderByIdAsc(int demandeId);
}
