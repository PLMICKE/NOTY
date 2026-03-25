package com.noty.model;

import jakarta.persistence.*;

@Entity
@Table(name = "paramettre")
public class Paramettre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "idmatiere")
    private Matiere matiere;

    @Column
    private Double nombredifference;

    @ManyToOne
    @JoinColumn(name = "idsigne")
    private Signe signe;

    @ManyToOne
    @JoinColumn(name = "idaction")
    private Action action;

    public Paramettre() {}

    public Paramettre(Matiere matiere, Double nombredifference, Signe signe, Action action) {
        this.matiere = matiere;
        this.nombredifference = nombredifference;
        this.signe = signe;
        this.action = action;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Matiere getMatiere() { return matiere; }
    public void setMatiere(Matiere matiere) { this.matiere = matiere; }

    public Double getNombredifference() { return nombredifference; }
    public void setNombredifference(Double nombredifference) { this.nombredifference = nombredifference; }

    public Signe getSigne() { return signe; }
    public void setSigne(Signe signe) { this.signe = signe; }

    public Action getAction() { return action; }
    public void setAction(Action action) { this.action = action; }
}
