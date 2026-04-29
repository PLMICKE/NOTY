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

    @Autowired
    private com.noty.service.DetailsDevisService detailsDevisService;
    
    double limite = 1000000;
    double remise = 10;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("devisList", devisService.findAll());
        model.addAttribute("devis", new Devis());
        model.addAttribute("typedevisList", typeDevisService.findAll());
        return "devis";
    }

    @PostMapping
    public String save(@ModelAttribute Devis devis,
            @RequestParam("ligneLibelle") List<String> libelles,
            @RequestParam("ligneMontant") List<String> montants,
            @RequestParam("ligneQuantite") List<String> quantites,
            @RequestParam(value = "observation", required = false) String observation) {

        List<DetailsDevis> lignes = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < libelles.size(); i++) {
            String lib = libelles.get(i);
            String mont = montants.get(i);
            String qteStr = quantites.get(i);
            
            if (lib != null && !lib.trim().isEmpty() && mont != null && !mont.trim().isEmpty() && qteStr != null && !qteStr.trim().isEmpty()) {
                Double montDouble = Double.parseDouble(mont.trim());
                int qte = Integer.parseInt(qteStr.trim());

                // Appliquer la remise si le montant unitaire atteint ou dépasse la limite
                if (montDouble >= limite) {
                    montDouble = montDouble - ((remise * montDouble) / 100.0);
                }

                DetailsDevis detail = new DetailsDevis();
                detail.setLibelle(lib.trim());
                BigDecimal montantFinal = BigDecimal.valueOf(montDouble).multiply(BigDecimal.valueOf(qte));
                detail.setMontant(montantFinal);
                detail.setQuantite(qte);
                
                // montantFinal contient déjà prix * quantité
                total = total.add(montantFinal);
                
                lignes.add(detail);
            }
        }

        devis.setMontantTotal(total);
        devisService.creerDevisComplet(devis, lignes, observation);
        return "redirect:/devis";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        devisService.deleteById(id);
        return "redirect:/devis";
    }

    @GetMapping("/demande/{demandeId}")
    public String detailDemandeClient(@PathVariable int demandeId, Model model) {
        model.addAttribute("demande", demandeService.findById(demandeId).orElse(null));
        
        List<Devis> devisList = devisService.findByDemandeId(demandeId);
        List<DetailsDevis> allDetails = new ArrayList<>();
        
        // Parcourir tous les devis de cette demande et récupérer leurs détails
        for (Devis devis : devisList) {
            allDetails.addAll(detailsDevisService.findByDevisId(devis.getId()));
        }
        
        model.addAttribute("devisList", devisList);
        model.addAttribute("detailsDevisList", allDetails);
        return "detaildemandeclient";
    }
}
