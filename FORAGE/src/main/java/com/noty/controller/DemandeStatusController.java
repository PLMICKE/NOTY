package com.noty.controller;

import com.noty.model.DemandeStatus;
import com.noty.service.CouleurService;
import com.noty.service.DemandeStatusService;
import com.noty.service.DemandeService;
import com.noty.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/demandestatus")
public class DemandeStatusController {

    @Autowired
    private DemandeStatusService demandeStatusService;

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private CouleurService couleurService;

    @GetMapping
    public String list(Model model) {
        List<DemandeStatus> dsList = demandeStatusService.findAll();
        model.addAttribute("demandeStatusList", dsList);
        model.addAttribute("demandeStatus", new DemandeStatus());
        model.addAttribute("demandes", demandeService.findAll());
        model.addAttribute("statusList", statusService.findAll());

        // Construire les maps couleur pour chaque colonne
        Map<Integer, String> couleurJoursMap = new HashMap<>();
        Map<Integer, String> couleurOuvrableMap = new HashMap<>();
        for (DemandeStatus ds : dsList) {
            if (ds.getNombreDeJours() != null) {
                couleurJoursMap.put(ds.getId(), couleurService.choixcouleur(ds.getNombreDeJours()));
            }
            if (ds.getNombreDeJoursOuvrable() != null) {
                couleurOuvrableMap.put(ds.getId(), couleurService.choixcouleur(ds.getNombreDeJoursOuvrable()));
            }
        }
        model.addAttribute("couleurJoursMap", couleurJoursMap);
        model.addAttribute("couleurOuvrableMap", couleurOuvrableMap);

        return "demandestatus";
    }

    @PostMapping
    public String save(@ModelAttribute DemandeStatus demandeStatus) {
        demandeStatusService.save(demandeStatus);
        return "redirect:/demandestatus";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        demandeStatusService.deleteById(id);
        return "redirect:/demandestatus";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        DemandeStatus ds = demandeStatusService.findById(id).orElse(null);
        model.addAttribute("editDs", ds);
        return "editdemandestatus";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id,
                         @RequestParam("date") java.time.LocalDateTime date,
                         @RequestParam("observation") String observation) {
        DemandeStatus ds = demandeStatusService.findById(id).orElse(null);
        if (ds != null) {
            ds.setDate(date);
            ds.setObservation(observation);
            demandeStatusService.save(ds);
        }
        return "redirect:/demandestatus";
    }

    @GetMapping("/demande/{id}")
    public String statusParDemande(@PathVariable int id, Model model) {
        model.addAttribute("demande", demandeService.findById(id).orElse(null));
        model.addAttribute("demandeStatusList", demandeStatusService.findByDemandeId(id));
        return "demandestatuspardemande";
    }
}
