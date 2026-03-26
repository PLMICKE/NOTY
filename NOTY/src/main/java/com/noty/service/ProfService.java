package com.noty.service;

import com.noty.model.Prof;
import com.noty.repository.ProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfService {

    @Autowired
    private ProfRepository profRepository;

    public List<Prof> findAll() {
        return profRepository.findAll();
    }

    public Optional<Prof> findById(int id) {
        return profRepository.findById(id);
    }

    public Prof save(Prof prof) {
        return profRepository.save(prof);
    }

    public void deleteById(int id) {
        profRepository.deleteById(id);
    }
}
