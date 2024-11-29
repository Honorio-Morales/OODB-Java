package com.miapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

class ConsultarDatos {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/veterinaria.odb");
        EntityManager em = emf.createEntityManager();

        // Empezamos una transacción
        em.getTransaction().begin();

        try {
            // Consulta para obtener todos los pacientes con nombre "Firulais"
            List<Paciente> pacientes = em.createQuery("SELECT p FROM Paciente p WHERE p.nombre = :nombre", Paciente.class)
                    .setParameter("nombre", "Firulais")
                    .getResultList();

            // Imprimir los resultados de los pacientes
            for (Paciente paciente : pacientes) {
                System.out.println("Nombre: " + paciente.getNombre());
                System.out.println("Especie: " + paciente.getEspecie());
                System.out.println("Raza: " + paciente.getRaza());
                System.out.println("Fecha de nacimiento: " + paciente.getFechaNacimiento());

                // Imprimir historial médico (visitas asociadas al paciente)
                for (Visita visita : paciente.obtenerHistorialMedico()) {
                    System.out.println("Visita - Fecha: " + visita.getFecha() + ", Motivo: " + visita.getMotivo());

                    // Imprimir los tratamientos asociados a la visita
                    for (Tratamiento tratamiento : visita.getTratamientos()) {
                        System.out.println("Tratamiento: " + tratamiento.obtenerDescripcion() + ", Costo: " + tratamiento.calcularCosto());
                    }
                }
            }

            // Consulta para obtener todos los veterinarios con nombre "Dr. López"
            List<Veterinario> veterinarios = em.createQuery("SELECT v FROM Veterinario v WHERE v.nombre = :nombre", Veterinario.class)
                    .setParameter("nombre", "Dr. López")
                    .getResultList();

            // Imprimir los resultados de los veterinarios
            for (Veterinario veterinario : veterinarios) {
                System.out.println("Veterinario: " + veterinario.getNombre());
                System.out.println("Especialidad: " + veterinario.getEspecialidad());

                // Imprimir pacientes asignados a este veterinario
                for (Paciente paciente : veterinario.obtenerPacientes()) {
                    System.out.println("Paciente asignado: " + paciente.getNombre());
                }
            }

            em.getTransaction().commit(); // Confirmar los cambios (aunque en este caso solo estamos leyendo)

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback(); // Hacer rollback si algo sale mal
        } finally {
            em.close();
            emf.close();
        }
    }
}
