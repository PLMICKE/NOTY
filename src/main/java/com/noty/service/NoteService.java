package com.noty.service;

import com.noty.model.Note;
import com.noty.model.Paramettre;
import com.noty.repository.NoteRepository;
import com.noty.repository.ParamettreRepository;
import com.noty.repository.CandidatRepository;
import com.noty.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ParamettreRepository paramettreRepository;

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Optional<Note> findById(int id) {
        return noteRepository.findById(id);
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(int id) {
        noteRepository.deleteById(id);
    }

    public Double calculateDynamicNote(int candidatId, int matiereId) {
        // 1. Récupérer les notes
        List<Note> notes = noteRepository.findByCandidatIdAndMatiereId(candidatId, matiereId);
        
        if (notes == null || notes.isEmpty()) {
            throw new RuntimeException("Aucune note trouvée pour ce candidat et cette matière.");
        }

        List<Double> valeursNotes = notes.stream()
                .map(Note::getNote)
                .toList();

        // Si une seule note ou toutes identiques
        long distinctNotes = valeursNotes.stream().distinct().count();
        double sumDiff = 0.0;

        if (valeursNotes.size() > 1 && distinctNotes > 1) {
            // Calculer la somme des différences absolues (paires distinctes)
            sumDiff = IntStream.range(0, valeursNotes.size())
                    .mapToDouble(i -> IntStream.range(i + 1, valeursNotes.size())
                            .mapToDouble(j -> Math.abs(valeursNotes.get(i) - valeursNotes.get(j)))
                            .sum())
                    .sum();
        }

        // Moyenne par défaut
        double avg = valeursNotes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        // 2. Récupérer la liste des Paramettres
        List<Paramettre> parametres = paramettreRepository.findByMatiereId(matiereId);
        if (parametres == null || parametres.isEmpty()) {
            throw new RuntimeException("Aucun paramètre trouvé pour cette matière.");
        }

        // 3. Evaluer chaque paramètre
        for (Paramettre param : parametres) {
            String nomSigne = param.getSigne().getNom().trim(); // ex: ">" ou "<"
            String nomAction = param.getAction().getNom().trim().toLowerCase(); // ex: "petit", "grand", "min", "max", "moyenne"
            double paramDiff = param.getNombredifference();

            boolean condition = false;

            // Compatibilité avec les noms textuels de signes (supposons ">" ou "<")
            if (nomSigne.equals(">")) {
                condition = sumDiff > paramDiff;
            } else if (nomSigne.equals("<")) {
                condition = sumDiff < paramDiff;
            } else if (nomSigne.equals(">=")) {
                condition = sumDiff >= paramDiff;
            } else if (nomSigne.equals("<=")) {
                condition = sumDiff <= paramDiff;
            } else if (nomSigne.equals("=")) {
                condition = sumDiff == paramDiff;
            }

            // 4. Si la condition est vraie pour ce paramètre, on applique l'action et on retourne le résultat !
            if (condition) {
                if (nomAction.equals("min") || nomAction.equals("petit")) {
                    return valeursNotes.stream().mapToDouble(Double::doubleValue).min().orElse(avg);
                } else if (nomAction.equals("max") || nomAction.equals("grand")) {
                    return valeursNotes.stream().mapToDouble(Double::doubleValue).max().orElse(avg);
                } else if (nomAction.equals("moyenne") || nomAction.equals("moyen")) {
                    return avg;
                }
            }
        }

        return avg; // Valeur par défaut si condition fausse
    }
}
