package com.noty.controller;

import com.noty.model.Candidat;
import com.noty.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/candidats")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("candidats", candidatService.findAll());
        model.addAttribute("candidat", new Candidat());
        return "candidats";
    }

    @PostMapping
    public String save(@ModelAttribute Candidat candidat) {
        candidatService.save(candidat);
        return "redirect:/candidats";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        candidatService.deleteById(id);
        return "redirect:/candidats";
    }
}
