package com.noty.model;

import jakarta.persistence.*;

@Entity
@Table(name = "candidat")
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(unique = true, nullable = false, length = 50)
    private String numero;

    public Candidat() {}

    public Candidat(String nom, String numero) {
        this.nom = nom;
        this.numero = numero;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
}
