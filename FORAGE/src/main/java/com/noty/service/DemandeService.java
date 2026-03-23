package com.noty.service;

import com.noty.model.Demande;
import com.noty.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    public List<Demande> findAll() {
        return demandeRepository.findAll();
    }

    public Optional<Demande> findById(int id) {
        return demandeRepository.findById(id);
    }

    public Demande save(Demande demande) {
        return demandeRepository.save(demande);
    }

    public void deleteById(int id) {
        demandeRepository.deleteById(id);
    }
}
