package com.noty.controller;

import com.noty.model.DemandeStatus;
import com.noty.service.DemandeStatusService;
import com.noty.service.DemandeService;
import com.noty.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demandestatus")
public class DemandeStatusController {

    @Autowired
    private DemandeStatusService demandeStatusService;

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private StatusService statusService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("demandeStatusList", demandeStatusService.findAll());
        model.addAttribute("demandeStatus", new DemandeStatus());
        model.addAttribute("demandes", demandeService.findAll());
        model.addAttribute("statusList", statusService.findAll());
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
}
