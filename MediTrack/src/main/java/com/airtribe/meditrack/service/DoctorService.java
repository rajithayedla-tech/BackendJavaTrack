package com.airtribe.meditrack.service;

import com.airtribe.meditrack.Interface.Searchable;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.enums.Specialization;
import com.airtribe.meditrack.util.DataStore;

import java.io.*;
import java.util.Collection;
import java.util.List;

public class DoctorService implements Searchable<Doctor> {

    private final DataStore<Doctor> store = new DataStore<>();

    public void addDoctor(Doctor doctor) {
        store.add(doctor);
    }

    public void viewDoctors() {
        store.getAll().forEach(d -> System.out.println(d.getSummary()));
    }

    @Override
    public Doctor searchById(String id) {
        for (Doctor d : store.getAll()) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        notFoundMessage();
        return null;
    }

    // SEARCH by Name (Overloading)
    public Doctor searchDoctor(String name) {
        for (Doctor d : store.getAll()) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        notFoundMessage();
        return null;
    }

    // SEARCH by Specialization (Overloading)
    public List<Doctor> searchDoctor(Specialization specialization) {
        return store.search(d -> d.getSpecialization() == specialization);
    }

    // STREAM: calculate average consultation fee
    public double averageConsultationFee() {
        return store.getAll()
                .stream()
                .mapToDouble(Doctor::getConsultationFee)
                .average()
                .orElse(0.0);
    }

    public void saveDoctorsToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("ID,Name,Age,Specialization,Fee");
            for (Doctor d : store.getAll()) {
                writer.printf("%s,%s,%d,%s,%.2f%n", d.getId(), d.getName(), d.getAge(), d.getSpecialization(), d.getConsultationFee());
            }
            System.out.println("Doctors saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving doctors to CSV: " + e.getMessage());
        }
    }

    public void loadDoctorsFromCSV(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    Specialization spec = Specialization.valueOf(parts[3].toUpperCase());
                    double fee = Double.parseDouble(parts[4]);
                    store.add(new Doctor(id, name, age, spec, fee));
                }
            }
            System.out.println("Doctors loaded from " + filename);
            viewDoctors(); // <-- show all doctors after loading
        } catch (IOException e) {
            System.out.println("Error loading doctors from CSV: " + e.getMessage());
        }
    }


    public long totalDoctors() {
        return store.getAll()
                .stream()
                .count();
    }

    public Collection<Doctor> getAllDoctors() {
        return store.getAll();
    }


}
