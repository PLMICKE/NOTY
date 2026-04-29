package com.noty.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Service
public class DateService {

    private static final Set<MonthDay> JOURS_FERIES = Set.of(
        MonthDay.of(1,  1),   // Nouvel An
        MonthDay.of(3,  29),  // Journée des martyrs
        MonthDay.of(5,  1),   // Fête du travail
        MonthDay.of(6,  26),  // Fête de l'Indépendance
        MonthDay.of(8,  15),  // Assomption
        MonthDay.of(11, 1),   // Toussaint
        MonthDay.of(12, 25)   // Noël
    );

    public Double nombreDeJours(LocalDateTime a, LocalDateTime b) {
        long minutes = ChronoUnit.MINUTES.between(a, b);
        long heures  = minutes / 60;
        long reste   = Math.abs(minutes % 60);

        if (reste >= 30) {
            if (heures < 0) {
                heures--;
            } else {
                heures++;
            }
        }

        return (double) heures;
    }

    public Double nombreDeJoursOuvrable(LocalDateTime a, LocalDateTime b) {
        LocalDate debut = a.toLocalDate();
        LocalDate fin   = b.toLocalDate();

        long totalMinutes = 0;
        LocalDate current = debut;

        while (!current.isAfter(fin)) {
            DayOfWeek jour = current.getDayOfWeek();
            boolean estWeekend = (jour == DayOfWeek.SATURDAY || jour == DayOfWeek.SUNDAY);
            boolean estFerie   = JOURS_FERIES.contains(MonthDay.from(current));

            if (!estWeekend && !estFerie) {
                LocalDateTime debutJour = current.atTime(8, 0);
                LocalDateTime finJour   = current.atTime(16, 0);

                LocalDateTime debutEffectif = a.isAfter(debutJour) ? a : debutJour;
                LocalDateTime finEffective  = b.isBefore(finJour)  ? b : finJour;

                if (debutEffectif.isBefore(finEffective)) {
                    totalMinutes += ChronoUnit.MINUTES.between(debutEffectif, finEffective);
                }
            }
            current = current.plusDays(1);
        }

        long heures = totalMinutes / 60;
        long reste  = Math.abs(totalMinutes % 60);

        if (reste >= 30) {
            if (heures < 0) {
                heures--;
            } else {
                heures++;
            }
        }

        return (double) heures;
    }
}
