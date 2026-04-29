package com.noty.service;

import com.noty.model.Devis;
import com.noty.model.DetailsDevis;
import com.noty.model.DemandeStatus;
import com.noty.model.Status;
import com.noty.model.TypeDevis;
import com.noty.repository.DevisRepository;
import com.noty.repository.DetailsDevisRepository;
import com.noty.repository.DemandeStatusRepository;
import com.noty.repository.StatusRepository;
import com.noty.repository.TypeDevisRepository;
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

    @Autowired
    private TypeDevisRepository typeDevisRepository;

    @Autowired
    private DateService dateService;

    @Autowired
    private DemandeStatusService demandeStatusService;

    public List<Devis> findAll() {
        return devisRepository.findAll();
    }

    public Optional<Devis> findById(int id) {
        return devisRepository.findById(id);
    }

    public Devis save(Devis devis) {
        return devisRepository.save(devis);
    }

    public List<Devis> findByDemandeId(int demandeId) {
        return devisRepository.findByDemandeId(demandeId);
    }

    public void deleteById(int id) {
        devisRepository.deleteById(id);
    }

    @Transactional
    public Devis creerDevisComplet(Devis devis, List<DetailsDevis> lignes, String observation) {
        // Default observation
        if (observation == null || observation.trim().isEmpty()) {
            observation = "pas d'observation";
        }

        // 1. Sauvegarder le devis
        Devis savedDevis = devisRepository.save(devis);

        // 2. Sauvegarder toutes les lignes de détail
        for (DetailsDevis ligne : lignes) {
            ligne.setDevis(savedDevis);
            detailsDevisRepository.save(ligne);
        }

        // 3. Récupérer le vrai TypeDevis depuis la BDD (le formulaire n'envoie que l'id, libelle est null)
        TypeDevis typeDevis = typeDevisRepository.findById(devis.getTypeDevis().getId()).orElse(null);

        Status statusCree;
        if (typeDevis != null && "preetude".equals(typeDevis.getLibelle())) {
            statusCree = statusRepository.findById(2).orElse(null);
        } else {
            statusCree = statusRepository.findById(3).orElse(null);
        }
        
        if (statusCree != null) {
            DemandeStatus ds = new DemandeStatus();
            ds.setDemande(savedDevis.getDemande());
            ds.setStatus(statusCree);
            ds.setDate(savedDevis.getDate().atStartOfDay());
            ds.setObservation(observation);

            demandeStatusService.save(ds);
        }

        return savedDevis;
    }
}
