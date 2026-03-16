package com.noty.service;

import com.noty.model.Paramettre;
import com.noty.repository.ParamettreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParamettreService {

    @Autowired
    private ParamettreRepository paramettreRepository;

    public List<Paramettre> findAll() {
        return paramettreRepository.findAll();
    }

    public Optional<Paramettre> findById(int id) {
        return paramettreRepository.findById(id);
    }

    public Paramettre save(Paramettre paramettre) {
        return paramettreRepository.save(paramettre);
    }

    public void deleteById(int id) {
        paramettreRepository.deleteById(id);
    }
}
