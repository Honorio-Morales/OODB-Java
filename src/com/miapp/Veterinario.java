package com.miapp;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Veterinario {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String especialidad;
    @ManyToMany
    private List<Paciente> listaPacientes = new ArrayList<>();

    public Veterinario() {}

    public Veterinario(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public void asignarPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
    }

    public List<Paciente> obtenerPacientes() {
        return listaPacientes;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }
}
