package com.noty.controller;

import com.noty.model.Action;
import com.noty.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actions")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("actions", actionService.findAll());
        model.addAttribute("action", new Action());
        return "actions";
    }

    @PostMapping
    public String save(@ModelAttribute Action action) {
        actionService.save(action);
        return "redirect:/actions";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        actionService.deleteById(id);
        return "redirect:/actions";
    }
}
