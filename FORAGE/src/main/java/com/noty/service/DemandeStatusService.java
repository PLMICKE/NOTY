package com.noty.service;

import com.noty.model.DemandeStatus;
import com.noty.repository.DemandeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeStatusService {

    @Autowired
    private DemandeStatusRepository demandeStatusRepository;

    public List<DemandeStatus> findAll() {
        return demandeStatusRepository.findAll();
    }

    public Optional<DemandeStatus> findById(int id) {
        return demandeStatusRepository.findById(id);
    }

    public DemandeStatus save(DemandeStatus demandeStatus) {
        return demandeStatusRepository.save(demandeStatus);
    }

    public void deleteById(int id) {
        demandeStatusRepository.deleteById(id);
    }
}
