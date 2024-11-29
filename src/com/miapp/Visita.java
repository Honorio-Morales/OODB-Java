package com.miapp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Visita {
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String motivo;
    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL)
    private List<Tratamiento> tratamientos = new ArrayList<>();
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Veterinario veterinario;

    public Visita() {}

    public Visita(Date fecha, String motivo, Paciente paciente, Veterinario veterinario) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.paciente = paciente;
        this.veterinario = veterinario;
    }

    public void agregarTratamiento(Tratamiento tratamiento) {
        tratamientos.add(tratamiento);
        tratamiento.setVisita(this);
    }

    public String obtenerDetalles() {
        return "Visita[id=" + id + ", fecha=" + fecha + ", motivo=" + motivo + "]";
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}
