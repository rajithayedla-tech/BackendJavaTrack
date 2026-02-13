package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.DataStore;

import java.util.Date;

public class AppointmentService {
    private final DataStore<Appointment> store = new DataStore<>();

    // CREATE
    public void createAppointment(String id, Doctor doctor, Patient patient, Date date) {
        store.add(new Appointment(id, doctor, patient, date));
    }

    // READ
    public void viewAppointments() {
        store.getAll().forEach(a -> System.out.println(a.summary()));
    }

    // SEARCH
    public Appointment getAppointmentById(String id) {
        return store.getAll().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new AppointmentNotFoundException("Appointment not found: " + id)
                );
    }

    // CANCEL
    public void cancelAppointment(String id) {
        Appointment appointment = getAppointmentById(id);
        appointment.setStatus(AppointmentStatus.CANCELLED);
    }
}
