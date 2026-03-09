package com.noty.controller;

import com.noty.model.Signe;
import com.noty.service.SigneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signes")
public class SigneController {

    @Autowired
    private SigneService signeService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("signes", signeService.findAll());
        model.addAttribute("signe", new Signe());
        return "signes";
    }

    @PostMapping
    public String save(@ModelAttribute Signe signe) {
        signeService.save(signe);
        return "redirect:/signes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        signeService.deleteById(id);
        return "redirect:/signes";
    }
}
