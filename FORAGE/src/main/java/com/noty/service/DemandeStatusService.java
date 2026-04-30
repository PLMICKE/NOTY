package com.noty.service;

import com.noty.model.DemandeStatus;
import com.noty.repository.DemandeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeStatusService {

    @Autowired
    private DemandeStatusRepository demandeStatusRepository;

    @Autowired
    private DateService dateService;

    public List<DemandeStatus> findAll() {
        return demandeStatusRepository.findAll();
    }

    public Optional<DemandeStatus> findById(int id) {
        return demandeStatusRepository.findById(id);
    }

    public DemandeStatus save(DemandeStatus demandeStatus) {
        List<DemandeStatus> list = demandeStatusRepository.findByDemandeIdOrderByIdAsc(demandeStatus.getDemande().getId());

        LocalDateTime datePrecedente = null;
        int indexCourant = -1; 

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == demandeStatus.getId()) {
                // C'est un update : on prend le status d'avant dans la liste
                indexCourant = i; 
                if (i > 0) {
                    datePrecedente = list.get(i - 1).getDate();
                }
                break;
            }
        }

        if (datePrecedente == null) {
            if (list.isEmpty() || demandeStatus.getId() == 0) {
                // Nouveau status : prendre le dernier existant
                if (!list.isEmpty()) {
                    datePrecedente = list.get(list.size() - 1).getDate();
                }
            }
        }

        if (datePrecedente != null) {
            demandeStatus.setNombreDeJours(dateService.nombreDeJours(datePrecedente, demandeStatus.getDate()));
            demandeStatus.setNombreDeJoursOuvrable(dateService.nombreDeJoursOuvrable(datePrecedente, demandeStatus.getDate()));
        } else {
            demandeStatus.setNombreDeJours(0.0);
            demandeStatus.setNombreDeJoursOuvrable(0.0);
        }

        // ✅ Mettre à jour le status derrière
        if (indexCourant >= 0 && indexCourant + 1 < list.size()) {
            DemandeStatus suivant = list.get(indexCourant + 1);
            suivant.setNombreDeJours(dateService.nombreDeJours(demandeStatus.getDate(), suivant.getDate()));
            suivant.setNombreDeJoursOuvrable(dateService.nombreDeJoursOuvrable(demandeStatus.getDate(), suivant.getDate()));
            demandeStatusRepository.save(suivant);
        }

        return demandeStatusRepository.save(demandeStatus);
    }

    public List<DemandeStatus> findByDemandeId(int demandeId) {
        return demandeStatusRepository.findByDemandeId(demandeId);
    }

    public LocalDateTime getDateDernierStatus(int demandeId) {
        List<DemandeStatus> list = demandeStatusRepository.findByDemandeIdOrderByIdAsc(demandeId);
        if (list.isEmpty()) {
            return LocalDateTime.now();
        }
        return list.get(list.size() - 1).getDate();
    }

    public void deleteById(int id) {
        demandeStatusRepository.deleteById(id);
    }
}