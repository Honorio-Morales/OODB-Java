package com.miapp;

import javax.persistence.*;

@Entity
public class Tratamiento {
    @Id
    @GeneratedValue
    private Long id;
    private String descripcion;
    private double costo;
    @ManyToOne
    private Visita visita;

    public Tratamiento() {}

    public Tratamiento(String descripcion, double costo) {
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public double calcularCosto() {
        return costo;
    }

    public String obtenerDescripcion() {
        return descripcion;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Visita getVisita() {
        return visita;
    }
}
