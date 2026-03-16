package com.noty.controller;

import com.noty.model.Paramettre;
import com.noty.service.ParamettreService;
import com.noty.service.MatiereService;
import com.noty.service.SigneService;
import com.noty.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paramettres")
public class ParamettreController {

    @Autowired
    private ParamettreService paramettreService;

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private SigneService signeService;

    @Autowired
    private ActionService actionService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("paramettres", paramettreService.findAll());
        model.addAttribute("paramettre", new Paramettre());
        model.addAttribute("matieres", matiereService.findAll());
        model.addAttribute("signes", signeService.findAll());
        model.addAttribute("actions", actionService.findAll());
        return "paramettres";
    }

    @PostMapping
    public String save(@ModelAttribute Paramettre paramettre) {
        paramettreService.save(paramettre);
        return "redirect:/paramettres";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        paramettreService.deleteById(id);
        return "redirect:/paramettres";
    }
}
