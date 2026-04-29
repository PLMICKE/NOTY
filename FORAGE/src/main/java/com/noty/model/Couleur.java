package com.noty.model;

import jakarta.persistence.*;

@Entity
@Table(name = "couleur")
public class Couleur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String maximum;

    @Column(length = 100)
    private String minimum;

    @Column(length = 100)
    private String loko;

    public Couleur() {
    }

    public Couleur(String maximum, String minimum, String couleur) {
        this.maximum = maximum;
        this.minimum = minimum;
        this.loko = couleur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getLoko() {
        return loko;
    }

    public void setLoko(String couleur) {
        this.loko = couleur;
    }
}
