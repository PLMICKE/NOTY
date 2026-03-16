package com.noty.controller;

import com.noty.model.Matiere;
import com.noty.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matieres")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("matieres", matiereService.findAll());
        model.addAttribute("matiere", new Matiere());
        return "matieres";
    }

    @PostMapping
    public String save(@ModelAttribute Matiere matiere) {
        matiereService.save(matiere);
        return "redirect:/matieres";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        matiereService.deleteById(id);
        return "redirect:/matieres";
    }
}
