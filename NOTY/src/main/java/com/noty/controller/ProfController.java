package com.noty.controller;

import com.noty.model.Prof;
import com.noty.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profs")
public class ProfController {

    @Autowired
    private ProfService profService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("profs", profService.findAll());
        model.addAttribute("prof", new Prof());
        return "profs";
    }

    @PostMapping
    public String save(@ModelAttribute Prof prof) {
        profService.save(prof);
        return "redirect:/profs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        profService.deleteById(id);
        return "redirect:/profs";
    }
}
