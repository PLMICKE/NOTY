package com.noty.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "details_devis")
public class DetailsDevis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "iddevis", nullable = false)
    private Devis devis;

    @Column(length = 255)
    private String libelle;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal montant;

    @Column(nullable = false)
    private int quantite;

    public DetailsDevis() {}

    public DetailsDevis(Devis devis, String libelle, BigDecimal montant, int quantite) {
        this.devis = devis;
        this.libelle = libelle;
        this.montant = montant;
        this.quantite = quantite;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Devis getDevis() { return devis; }
    public void setDevis(Devis devis) { this.devis = devis; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}
