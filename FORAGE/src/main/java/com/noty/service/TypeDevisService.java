package com.noty.service;

import com.noty.model.TypeDevis;
import com.noty.repository.TypeDevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeDevisService {

    @Autowired
    private TypeDevisRepository typeDevisRepository;

    public List<TypeDevis> findAll() {
        return typeDevisRepository.findAll();
    }

    public Optional<TypeDevis> findById(int id) {
        return typeDevisRepository.findById(id);
    }

    public TypeDevis save(TypeDevis typeDevis) {
        return typeDevisRepository.save(typeDevis);
    }

    public void deleteById(int id) {
        typeDevisRepository.deleteById(id);
    }
}
