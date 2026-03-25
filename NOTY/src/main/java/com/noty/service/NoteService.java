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
            //diff total
            sumDiff = IntStream.range(0, valeursNotes.size())
                    .mapToDouble(i -> IntStream.range(i + 1, valeursNotes.size())
                            .mapToDouble(j -> Math.abs(valeursNotes.get(i) - valeursNotes.get(j)))
                            .sum())
                    .sum();
        }

        // Moyenne par défaut
        double avg = valeursNotes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        // liste param
        List<Paramettre> parametres = paramettreRepository.findByMatiereId(matiereId);
        if (parametres == null || parametres.isEmpty()) {
            throw new RuntimeException("Aucun paramètre trouvé pour cette matière.");
        }

        // Étape 3a : Filtrer les conditions satisfaites
        List<Paramettre> conditionsSatisfaites = filtrerConditionsSatisfaites(parametres, sumDiff);

        if (conditionsSatisfaites.isEmpty()) {
            return avg; // Aucune condition satisfaite → moyenne par défaut
        }

        // Étape 3b : Résoudre le conflit par règle de proximité
        Paramettre gagnant = resoudreConflitParProximite(conditionsSatisfaites, sumDiff);

        // Étape 3c : Appliquer l'action du gagnant
        return appliquerAction(gagnant, valeursNotes, avg);
    }

    // ── Fonctions auxiliaires ──────────────────────────────────────────────────

    /** Évalue si une condition (signe + seuil) est satisfaite pour la valeur donnée. */
    private boolean estSatisfaite(Paramettre param, double valeur) {
        String signe = param.getSigne().getNom().trim();
        double seuil = param.getNombredifference();
        return switch (signe) {
            case ">"  -> valeur > seuil;
            case "<"  -> valeur < seuil;
            case ">=" -> valeur >= seuil;
            case "<=" -> valeur <= seuil;
            case "="  -> valeur == seuil;
            default   -> false;
        };
    }

    /** Filtre et retourne uniquement les conditions dont la condition est vraie. */
    private List<Paramettre> filtrerConditionsSatisfaites(List<Paramettre> parametres, double valeur) {
        return parametres.stream()
                .filter(p -> estSatisfaite(p, valeur))
                .toList();
    }

    /** Calcule la distance entre la valeur et le seuil d'un paramètre. */
    private double calculerDistance(Paramettre param, double valeur) {
        return Math.abs(valeur - param.getNombredifference());
    }

    /**
     * Résout le conflit entre plusieurs conditions satisfaites par la règle de proximité :
     * on choisit celle dont le seuil est le plus proche de la valeur.
     * En cas d'égalité de distance, on prend le plus petit seuil.
     */
    private Paramettre resoudreConflitParProximite(List<Paramettre> satisfaites, double valeur) {
        Paramettre gagnant = satisfaites.get(0);
        double distanceMin = calculerDistance(gagnant, valeur);

        for (int i = 1; i < satisfaites.size(); i++) {
            Paramettre candidat = satisfaites.get(i);
            double distance = calculerDistance(candidat, valeur);

            boolean plusProche = distance < distanceMin;
            boolean egaliteEtSeuilPlusPetit = (distance == distanceMin)
                    && (candidat.getNombredifference() < gagnant.getNombredifference());

            if (plusProche || egaliteEtSeuilPlusPetit) {
                gagnant = candidat;
                distanceMin = distance;
            }
        }
        return gagnant;
    }

    /** Applique l'action du paramètre gagnant sur la liste des notes. */
    private double appliquerAction(Paramettre param, List<Double> notes, double avg) {
        String action = param.getAction().getNom().trim().toLowerCase();
        return switch (action) {
            case "min", "petit"         -> notes.stream().mapToDouble(Double::doubleValue).min().orElse(avg);
            case "max", "grand"         -> notes.stream().mapToDouble(Double::doubleValue).max().orElse(avg);
            case "moyenne", "moyen"     -> avg;
            default                     -> avg;
        };
    }
}
