package com.noty.controller;

import com.noty.model.Demande;
import com.noty.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/devis")
public class DevisApiController {

    @Autowired
    private DemandeService demandeService;

    @GetMapping("/demande/{id}")
    public ResponseEntity<?> getDemandeInfo(@PathVariable int id) {
        Optional<Demande> opt = demandeService.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Demande d = opt.get();
        Map<String, String> info = new HashMap<>();
        info.put("id", String.valueOf(d.getId()));
        info.put("lieu", d.getLieu() != null ? d.getLieu() : "");
        info.put("districk", d.getDistrick() != null ? d.getDistrick() : "");
        info.put("date", d.getDate() != null ? d.getDate().toString() : "");
        info.put("clientNom", d.getClient() != null ? d.getClient().getNom() : "");
        info.put("clientContact", d.getClient() != null && d.getClient().getContact() != null ? d.getClient().getContact() : "");
        return ResponseEntity.ok(info);
    }
}