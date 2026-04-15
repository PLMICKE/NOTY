package com.noty.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "demande_status")
public class DemandeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "iddemande", nullable = false)
    private Demande demande;

    @ManyToOne
    @JoinColumn(name = "idstatus", nullable = false)
    private Status status;

    @Column
    private LocalDateTime date;

    @Column(length = 500)
    private String observation;

    public DemandeStatus() {}

    public DemandeStatus(Demande demande, Status status, LocalDateTime date, String observation) {
        this.demande = demande;
        this.status = status;
        this.date = date;
        this.observation = observation;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Demande getDemande() { return demande; }
    public void setDemande(Demande demande) { this.demande = demande; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }
}
