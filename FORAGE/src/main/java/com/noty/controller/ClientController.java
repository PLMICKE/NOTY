package com.noty.controller;

import com.noty.model.Client;
import com.noty.service.ClientService;
import com.noty.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private DemandeService demandeService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("client", new Client());
        return "clients";
    }

    @PostMapping
    public String save(@ModelAttribute Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        clientService.deleteById(id);
        return "redirect:/clients";
    }

    @GetMapping("/demandes/{id}")
    public String demandesClient(@PathVariable int id, Model model) {
        Client client = clientService.findById(id).orElse(null);
        model.addAttribute("client", client);
        model.addAttribute("demandes", demandeService.findByClientId(id));
        return "clientdemande";
    }
}