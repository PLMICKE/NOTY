package com.noty.controller;

import com.noty.model.Note;
import com.noty.service.NoteService;
import com.noty.service.CandidatService;
import com.noty.service.ProfService;
import com.noty.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private CandidatService candidatService;

    @Autowired
    private ProfService profService;

    @Autowired
    private MatiereService matiereService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("notes", noteService.findAll());
        model.addAttribute("note", new Note());
        model.addAttribute("candidats", candidatService.findAll());
        model.addAttribute("profs", profService.findAll());
        model.addAttribute("matieres", matiereService.findAll());
        return "notes";
    }

    @PostMapping
    public String save(@ModelAttribute Note note) {
        noteService.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        noteService.deleteById(id);
        return "redirect:/notes";
    }

    @GetMapping("/calculer")
    public String showCalculForm(Model model) {
        model.addAttribute("candidats", candidatService.findAll());
        model.addAttribute("matieres", matiereService.findAll());
        return "calcul";
    }

    @PostMapping("/calculer")
    public String doCalculate(@RequestParam int candidatId, @RequestParam int matiereId, Model model) {
        try {
            Double result = noteService.calculateDynamicNote(candidatId, matiereId);
            model.addAttribute("result", result);
            model.addAttribute("candidat", candidatService.findById(candidatId).orElse(null));
            model.addAttribute("matiere", matiereService.findById(matiereId).orElse(null));
            return "resultat";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("candidats", candidatService.findAll());
            model.addAttribute("matieres", matiereService.findAll());
            return "calcul";
        }
    }
}
