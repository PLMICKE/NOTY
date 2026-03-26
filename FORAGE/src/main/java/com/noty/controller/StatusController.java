package com.noty.controller;

import com.noty.model.Status;
import com.noty.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("statusList", statusService.findAll());
        model.addAttribute("status", new Status());
        return "status";
    }

    @PostMapping
    public String save(@ModelAttribute Status status) {
        statusService.save(status);
        return "redirect:/status";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        statusService.deleteById(id);
        return "redirect:/status";
    }
}
