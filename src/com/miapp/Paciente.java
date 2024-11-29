package com.miapp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Paciente {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Visita> historialMedico = new ArrayList<>();

    public Paciente() {}

    public Paciente(String nombre, String especie, String raza, Date fechaNacimiento) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
    }

    public void agregarVisita(Visita visita) {
        historialMedico.add(visita);
        visita.setPaciente(this);
    }

    public List<Visita> obtenerHistorialMedico() {
        return historialMedico;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
