package com.miapp;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class DBService {
    private EntityManagerFactory emf;
    private EntityManager em;
    private Scanner scanner;

    public DBService() {
        emf = Persistence.createEntityManagerFactory("objectdb:database.odb");
        em = emf.createEntityManager();
        scanner = new Scanner(System.in);
    }

    // Método para agregar un nuevo paciente
    public void agregarPaciente() {
        Paciente paciente = new Paciente();
        System.out.print("Ingrese el nombre del paciente: ");
        paciente.setNombre(scanner.nextLine());
        System.out.print("Ingrese la especie del paciente: ");
        paciente.setEspecie(scanner.nextLine());
        System.out.print("Ingrese la raza del paciente: ");
        paciente.setRaza(scanner.nextLine());
        paciente.setFechaNacimiento(new java.util.Date());

        em.getTransaction().begin();
        em.persist(paciente);
        em.getTransaction().commit();

        System.out.println("Paciente agregado exitosamente.");
    }

    // Método para agregar una visita
    public void agregarVisita() {
        System.out.print("Ingrese el ID del paciente para la visita: ");
        Long pacienteId = Long.parseLong(scanner.nextLine());
        Paciente paciente = em.find(Paciente.class, pacienteId);

        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        Visita visita = new Visita();
        System.out.print("Ingrese la fecha de la visita (dd/MM/yyyy): ");
        String fechaStr = scanner.nextLine();
        // Aquí puedes agregar lógica para convertir la fecha de String a Date

        System.out.print("Ingrese el motivo de la visita: ");
        visita.setMotivo(scanner.nextLine());

        System.out.print("Ingrese el nombre del veterinario: ");
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(scanner.nextLine());

        visita.setVeterinario(veterinario);
        visita.setFecha(new java.util.Date());

        paciente.agregarVisita(visita);

        em.getTransaction().begin();
        em.persist(veterinario);  // Si el veterinario es nuevo
        em.persist(visita);
        em.getTransaction().commit();

        System.out.println("Visita agregada exitosamente.");
    }

    // Método para consultar pacientes
    public void consultarPacientes() {
        List<Paciente> pacientes = em.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();
        System.out.println("Pacientes en la base de datos:");
        for (Paciente p : pacientes) {
            System.out.println(p.getNombre() + " - " + p.getEspecie());
        }
    }

    // Método para cerrar la conexión a la base de datos
    public void cerrar() {
        em.close();
        emf.close();
    }
}
