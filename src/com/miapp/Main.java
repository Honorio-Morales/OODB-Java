package com.miapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
        // Crear la fábrica y el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/veterinaria.odb");
        EntityManager em = emf.createEntityManager();

        // Iniciar una transacción
        em.getTransaction().begin();

        // Crear 1 veterinario
        Veterinario veterinario = new Veterinario("Dr. Martínez", "Odontología");

        // Crear 1 paciente
        Paciente paciente = new Paciente("Bella", "Perro", "Golden Retriever", new Date());

        // Crear 1 tratamiento
        Tratamiento tratamiento = new Tratamiento("Desparacitación interna", 40.0);

        // Crear 1 visita
        Visita visita = new Visita(new Date(), "Chequeo general", paciente, veterinario);
        visita.agregarTratamiento(tratamiento);

        // Asociar la visita al paciente y el paciente al veterinario
        paciente.agregarVisita(visita);
        veterinario.asignarPaciente(paciente);

        // Persistir los objetos en la base de datos
        em.persist(veterinario);
        em.persist(paciente);
        em.persist(tratamiento);
        em.persist(visita);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Cerrar el EntityManager
        em.close();
        emf.close();
        */



        // Crear la fábrica y el EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/veterinaria.odb");
        EntityManager em = emf.createEntityManager();

        // Crear la ventana JFrame
        JFrame frame = new JFrame("Veterinaria - Datos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // Uso de BoxLayout para organizar

        // Crear modelo de tabla para los pacientes
        DefaultTableModel modelPacientes = new DefaultTableModel();
        modelPacientes.addColumn("Nombre");
        modelPacientes.addColumn("Especie");
        modelPacientes.addColumn("Raza");
        modelPacientes.addColumn("Fecha de Nacimiento");

        // Consultar todos los pacientes
        List<Paciente> pacientes = em.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();
        for (Paciente paciente : pacientes) {
            modelPacientes.addRow(new Object[]{
                    paciente.getNombre(),
                    paciente.getEspecie(),
                    paciente.getRaza(),
                    paciente.getFechaNacimiento()
            });
        }

        // Crear JTable para los pacientes
        JTable tablePacientes = new JTable(modelPacientes);
        JScrollPane scrollPanePacientes = new JScrollPane(tablePacientes);

        // Crear el panel para los pacientes
        JPanel panelPacientes = new JPanel();
        panelPacientes.setLayout(new BoxLayout(panelPacientes, BoxLayout.Y_AXIS));  // Layout vertical
        JLabel labelPacientes = new JLabel("Lista de Pacientes");
        labelPacientes.setFont(labelPacientes.getFont().deriveFont(16.0f)); // Cambiar el tamaño de la fuente
        panelPacientes.add(labelPacientes);
        panelPacientes.add(scrollPanePacientes);

        // Agregar el panel de pacientes al JFrame
        frame.add(panelPacientes);

        // Consultar todos los veterinarios
        DefaultTableModel modelVeterinarios = new DefaultTableModel();
        modelVeterinarios.addColumn("Nombre");
        modelVeterinarios.addColumn("Especialidad");

        List<Veterinario> veterinarios = em.createQuery("SELECT v FROM Veterinario v", Veterinario.class).getResultList();
        for (Veterinario veterinario : veterinarios) {
            modelVeterinarios.addRow(new Object[]{
                    veterinario.getNombre(),
                    veterinario.getEspecialidad()
            });
        }

        // Crear JTable para los veterinarios
        JTable tableVeterinarios = new JTable(modelVeterinarios);
        JScrollPane scrollPaneVeterinarios = new JScrollPane(tableVeterinarios);

        // Crear el panel para los veterinarios
        JPanel panelVeterinarios = new JPanel();
        panelVeterinarios.setLayout(new BoxLayout(panelVeterinarios, BoxLayout.Y_AXIS));  // Layout vertical
        JLabel labelVeterinarios = new JLabel("Lista de Veterinarios");
        labelVeterinarios.setFont(labelVeterinarios.getFont().deriveFont(16.0f)); // Cambiar el tamaño de la fuente
        panelVeterinarios.add(labelVeterinarios);
        panelVeterinarios.add(scrollPaneVeterinarios);

        // Agregar el panel de veterinarios al JFrame
        frame.add(panelVeterinarios);

        // Hacer visible la ventana
        frame.setVisible(true);

        em.close();
        emf.close();


    }
}
