package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.entity.enums.AppointmentStatus;

public class Appointment implements Cloneable{
    private Patient patient;
    private Doctor doctor;
    private AppointmentStatus status;

    public Appointment(Patient patient, Doctor doctor) {
        this.patient = patient.clone();   // deep copy
        this.doctor = doctor;
        this.status = AppointmentStatus.CONFIRMED;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

    @Override
    public Appointment clone() {
        try {
            Appointment copy = (Appointment) super.clone();
            copy.patient = this.patient.clone(); // deep copy
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Appointment clone failed", e);
        }
    }

    public String summary() {
        return "Patient: " + patient.getName()
                + " | Doctor: " + doctor.getName()
                + " | Status: " + status;
    }
}
