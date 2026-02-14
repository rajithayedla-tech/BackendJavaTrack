package com.airtribe.meditrack.service;

import com.airtribe.meditrack.Interface.Searchable;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.DataStore;

import java.io.*;

public class PatientService implements Searchable<Patient> {
    private final DataStore<Patient> store = new DataStore<>();

    // CREATE
    public void addPatient(Patient patient) {
        store.add(patient);
    }

    // READ (ALL)
    public void viewPatients() {
        store.getAll().forEach(p -> System.out.println(p.getSummary()));
    }

    // SEARCH (Polymorphism via interface)
    @Override
    public Patient searchById(String id) {
        for (Patient p : store.getAll()) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        notFoundMessage();
        return null;
    }

    // SEARCH by Name (Overloading)
    public Patient searchPatient(String name) {
        for (Patient p : store.getAll()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        notFoundMessage();
        return null;
    }

    // SEARCH by Age (Overloading)
    public Patient searchPatient(int age) {
        for (Patient p : store.getAll()) {
            if (p.getAge() == age) {
                return p;
            }
        }
        notFoundMessage();
        return null;
    }

    public void savePatientsToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Header row
            writer.println("ID,Name,Age,Ailment");
            // Data rows
            for (Patient p : store.getAll()) {
                writer.printf("%s,%s,%d%n", p.getId(), p.getName(), p.getAge());
            }
            System.out.println("Patients saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving patients to CSV: " + e.getMessage());
        }
    }

    public void loadPatientsFromCSV(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String ailment = parts[3];
                    store.add(new Patient(id, name, age));
                }
            }
            System.out.println("Patients loaded from " + filename);
            viewPatients(); // <-- show all patients after loading
        } catch (IOException e) {
            System.out.println("Error loading patients from CSV: " + e.getMessage());
        }
    }
}
