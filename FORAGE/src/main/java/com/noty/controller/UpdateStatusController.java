package com.noty.controller;

import com.noty.model.DemandeStatus;
import com.noty.service.DemandeService;
import com.noty.service.DemandeStatusService;
import com.noty.service.DateService;
import com.noty.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/updatestatus")
public class UpdateStatusController {

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private DemandeStatusService demandeStatusService;

    @Autowired
    private DateService dateService;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("demandes", demandeService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        return "updatestatus";
    }

    @PostMapping
    public String save(@ModelAttribute DemandeStatus demandeStatus,
                        @RequestParam(value = "observation", required = false) String observation) {
        if (observation == null || observation.trim().isEmpty()) {
            observation = "pas d'observation";
        }
        demandeStatus.setObservation(observation);

        demandeStatusService.save(demandeStatus);
        return "redirect:/demandestatus";
    }
}