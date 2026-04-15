package com.noty.controller;

import com.noty.model.TypeDevis;
import com.noty.service.TypeDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/typedevis")
public class TypeDevisController {

    @Autowired
    private TypeDevisService typeDevisService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("typedevisList", typeDevisService.findAll());
        model.addAttribute("typedevis", new TypeDevis());
        return "typedevis";
    }

    @PostMapping
    public String save(@ModelAttribute TypeDevis typeDevis) {
        typeDevisService.save(typeDevis);
        // Exception et RuntimeException
        return "redirect:/typedevis";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        typeDevisService.deleteById(id);
        return "redirect:/typedevis";
    }
}
