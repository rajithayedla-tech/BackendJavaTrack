package com.airtribe.meditrack.service;

import com.airtribe.meditrack.Interface.Searchable;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.DataStore;

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

}
