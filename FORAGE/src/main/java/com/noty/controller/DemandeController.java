package com.noty.controller;

import com.noty.model.Demande;
import com.noty.service.DemandeService;
import com.noty.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("demandes", demandeService.findAll());
        model.addAttribute("demande", new Demande());
        model.addAttribute("clients", clientService.findAll());
        return "demandes";
    }

    @PostMapping
    public String save(@ModelAttribute Demande demande) {
        demandeService.save(demande);
        return "redirect:/demandes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        demandeService.deleteById(id);
        return "redirect:/demandes";
    }
}
