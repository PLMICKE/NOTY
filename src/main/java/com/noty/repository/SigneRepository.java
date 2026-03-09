package com.noty.repository;

import com.noty.model.Signe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SigneRepository extends JpaRepository<Signe, Integer> {
}
