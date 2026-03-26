package com.noty.controller;

import com.noty.model.DetailsDevis;
import com.noty.service.DetailsDevisService;
import com.noty.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/detailsdevis")
public class DetailsDevisController {

    @Autowired
    private DetailsDevisService detailsDevisService;

    @Autowired
    private DevisService devisService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("detailsDevisList", detailsDevisService.findAll());
        model.addAttribute("detailsDevis", new DetailsDevis());
        model.addAttribute("devisList", devisService.findAll());
        return "detailsdevis";
    }

    @PostMapping
    public String save(@ModelAttribute DetailsDevis detailsDevis) {
        detailsDevisService.save(detailsDevis);
        return "redirect:/detailsdevis";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        detailsDevisService.deleteById(id);
        return "redirect:/detailsdevis";
    }
}
