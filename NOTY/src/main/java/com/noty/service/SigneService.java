package com.noty.service;

import com.noty.model.Signe;
import com.noty.repository.SigneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SigneService {

    @Autowired
    private SigneRepository signeRepository;

    public List<Signe> findAll() {
        return signeRepository.findAll();
    }

    public Optional<Signe> findById(int id) {
        return signeRepository.findById(id);
    }

    public Signe save(Signe signe) {
        return signeRepository.save(signe);
    }

    public void deleteById(int id) {
        signeRepository.deleteById(id);
    }
}
