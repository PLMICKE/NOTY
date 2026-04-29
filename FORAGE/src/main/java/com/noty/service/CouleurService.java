package com.noty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noty.model.Couleur;
import com.noty.repository.CouleurRepository;

@Service
public class CouleurService {

    @Autowired
    private CouleurRepository couleurRepository;

    public List<Couleur> findAll() {
        return couleurRepository.findAll();
    }

    public Optional<Couleur> findById(int id) {
        return couleurRepository.findById(id);
    }

    public Couleur save(Couleur couleur) {
        return couleurRepository.save(couleur);
    }

    public void deleteById(int id) {
        couleurRepository.deleteById(id);
    }

    public String choixcouleur(double valeur) {
        List<Couleur> couleurs = couleurRepository.findAll();
        for (Couleur couleur : couleurs) {
            if (valeur >= Double.parseDouble(couleur.getMinimum())
                    && valeur <= Double.parseDouble(couleur.getMaximum())) {
                return couleur.getLoko();
            }
        }
        return "";
    }
}
