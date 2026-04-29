package com.noty.controller;

import com.noty.model.Couleur;
import com.noty.service.CouleurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/couleur")
public class CouleurController {

    @Autowired
    private CouleurService couleurService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("couleurList", couleurService.findAll());
        model.addAttribute("couleur", new Couleur());
        return "couleur";
    }

    @PostMapping
    public String save(@ModelAttribute("couleurForm") Couleur couleurForm) {
        couleurService.save(couleurForm);
        return "redirect:/couleur";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        couleurService.deleteById(id);
        return "redirect:/couleur";
    }
}
