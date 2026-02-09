package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.DataStore;

public class AppointmentService {
    private final DataStore<Appointment> store = new DataStore<>();

    // CREATE
    public void createAppointment(Patient patient, Doctor doctor) {
        store.add(new Appointment(patient, doctor));
    }

    // READ
    public void viewAppointments() {
        store.getAll().forEach(a -> System.out.println(a.summary()));
    }
}
