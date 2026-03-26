package com.noty.controller;

import com.noty.model.Devis;
import com.noty.model.DetailsDevis;
import com.noty.service.DevisService;
import com.noty.service.TypeDevisService;
import com.noty.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/devis")
public class DevisController {

    @Autowired
    private DevisService devisService;

    @Autowired
    private TypeDevisService typeDevisService;

    @Autowired
    private DemandeService demandeService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("devisList", devisService.findAll());
        model.addAttribute("devis", new Devis());
        model.addAttribute("typedevisList", typeDevisService.findAll());
        model.addAttribute("demandes", demandeService.findAll());
        return "devis";
    }

    @PostMapping
    public String save(@ModelAttribute Devis devis,
                       @RequestParam("ligneLibelle") List<String> libelles,
                       @RequestParam("ligneMontant") List<String> montants) {

        List<DetailsDevis> lignes = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < libelles.size(); i++) {
            String lib = libelles.get(i);
            String mont = montants.get(i);
            if (lib != null && !lib.trim().isEmpty() && mont != null && !mont.trim().isEmpty()) {
                DetailsDevis detail = new DetailsDevis();
                detail.setLibelle(lib.trim());
                BigDecimal montant = new BigDecimal(mont.trim());
                detail.setMontant(montant);
                total = total.add(montant);
                lignes.add(detail);
            }
        }

        devis.setMontantTotal(total);
        devisService.creerDevisComplet(devis, lignes);
        return "redirect:/devis";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        devisService.deleteById(id);
        return "redirect:/devis";
    }
}
