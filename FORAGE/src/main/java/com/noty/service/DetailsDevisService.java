package com.noty.service;

import com.noty.model.DetailsDevis;
import com.noty.repository.DetailsDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsDevisService {

    @Autowired
    private DetailsDevisRepository detailsDevisRepository;

    public List<DetailsDevis> findAll() {
        return detailsDevisRepository.findAll();
    }

    public Optional<DetailsDevis> findById(int id) {
        return detailsDevisRepository.findById(id);
    }

    public DetailsDevis save(DetailsDevis detailsDevis) {
        return detailsDevisRepository.save(detailsDevis);
    }

    public List<DetailsDevis> findByDevisId(int devisId) {
        return detailsDevisRepository.findByDevisId(devisId);
    }

    public void deleteById(int id) {
        detailsDevisRepository.deleteById(id);
    }
}
