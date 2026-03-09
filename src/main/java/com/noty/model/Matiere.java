package com.noty.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matiere")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column
    private Integer coefficient;

    public Matiere() {}

    public Matiere(String nom, Integer coefficient) {
        this.nom = nom;
        this.coefficient = coefficient;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Integer getCoefficient() { return coefficient; }
    public void setCoefficient(Integer coefficient) { this.coefficient = coefficient; }
}
