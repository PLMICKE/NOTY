package com.noty.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "montant_total", precision = 15, scale = 2)
    private BigDecimal montantTotal;

    @ManyToOne
    @JoinColumn(name = "id_typedevis", nullable = false)
    private TypeDevis typeDevis;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "iddemande", nullable = false)
    private Demande demande;

    public Devis() {}

    public Devis(BigDecimal montantTotal, TypeDevis typeDevis, LocalDate date, Demande demande) {
        this.montantTotal = montantTotal;
        this.typeDevis = typeDevis;
        this.date = date;
        this.demande = demande;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public BigDecimal getMontantTotal() { return montantTotal; }
    public void setMontantTotal(BigDecimal montantTotal) { this.montantTotal = montantTotal; }

    public TypeDevis getTypeDevis() { return typeDevis; }
    public void setTypeDevis(TypeDevis typeDevis) { this.typeDevis = typeDevis; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Demande getDemande() { return demande; }
    public void setDemande(Demande demande) { this.demande = demande; }
}
