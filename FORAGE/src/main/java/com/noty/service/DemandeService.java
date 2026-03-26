package com.noty.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noty.model.Demande;
import com.noty.model.DemandeStatus;
import com.noty.repository.DemandeRepository;

import com.noty.model.Status;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DemandeStatusService demandeStatusService;

    @Autowired
    private StatusService statusService;


    public List<Demande> findAll() {
        return demandeRepository.findAll();
    }

    public Optional<Demande> findById(int id) {
        return demandeRepository.findById(id);
    }

    public Demande save(Demande demande) {
        Demande newDemande = demandeRepository.save(demande);
        Status statutCreate = statusService.findById(1).get();
        DemandeStatus newDemandeStatus = new DemandeStatus(newDemande, statutCreate, LocalDateTime.now());
        demandeStatusService.save(newDemandeStatus);
        return newDemande;
    }

    public void deleteById(int id) {
        demandeRepository.deleteById(id);
    }
}
