package com.noty.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "demande")
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "idclient", nullable = false)
    private Client client;

    @Column(length = 100)
    private String lieu;

    @Column(length = 100)
    private String districk;

    public Demande() {}

    public Demande(LocalDate date, Client client, String lieu, String districk) {
        this.date = date;
        this.client = client;
        this.lieu = lieu;
        this.districk = districk;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }

    public String getDistrick() { return districk; }
    public void setDistrick(String districk) { this.districk = districk; }
}
