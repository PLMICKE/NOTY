package com.noty.model;

import jakarta.persistence.*;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idetudiant")
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "idprof")
    private Prof prof;

    @ManyToOne
    @JoinColumn(name = "idmatiere")
    private Matiere matiere;

    @Column
    private Double note;

    public Note() {}

    public Note(Candidat candidat, Prof prof, Matiere matiere, Double note) {
        this.candidat = candidat;
        this.prof = prof;
        this.matiere = matiere;
        this.note = note;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Candidat getCandidat() { return candidat; }
    public void setCandidat(Candidat candidat) { this.candidat = candidat; }

    public Prof getProf() { return prof; }
    public void setProf(Prof prof) { this.prof = prof; }

    public Matiere getMatiere() { return matiere; }
    public void setMatiere(Matiere matiere) { this.matiere = matiere; }

    public Double getNote() { return note; }
    public void setNote(Double note) { this.note = note; }
}
