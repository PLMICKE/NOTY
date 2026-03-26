package com.noty.service;

import com.noty.model.Devis;
import com.noty.model.DetailsDevis;
import com.noty.model.DemandeStatus;
import com.noty.model.Status;
import com.noty.repository.DevisRepository;
import com.noty.repository.DetailsDevisRepository;
import com.noty.repository.DemandeStatusRepository;
import com.noty.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private DetailsDevisRepository detailsDevisRepository;

    @Autowired
    private DemandeStatusRepository demandeStatusRepository;

    @Autowired
    private StatusRepository statusRepository;

    public List<Devis> findAll() {
        return devisRepository.findAll();
    }

    public Optional<Devis> findById(int id) {
        return devisRepository.findById(id);
    }

    public Devis save(Devis devis) {
        return devisRepository.save(devis);
    }

    public void deleteById(int id) {
        devisRepository.deleteById(id);
    }

    @Transactional
    public Devis creerDevisComplet(Devis devis, List<DetailsDevis> lignes) {
        // 1. Sauvegarder le devis
        Devis savedDevis = devisRepository.save(devis);

        // 2. Sauvegarder toutes les lignes de détail
        for (DetailsDevis ligne : lignes) {
            ligne.setDevis(savedDevis);
            detailsDevisRepository.save(ligne);
        }

        // // 3. Insérer dans demande_status (idstatus = 1)
        // Status statusCree = statusRepository.findById(1).orElse(null);
        // if (statusCree != null) {
        //     DemandeStatus ds = new DemandeStatus();
        //     ds.setDemande(savedDevis.getDemande());
        //     ds.setStatus(statusCree);
        //     ds.setDate(LocalDateTime.now());
        //     demandeStatusRepository.save(ds);
        // }

        return savedDevis;
    }
}
