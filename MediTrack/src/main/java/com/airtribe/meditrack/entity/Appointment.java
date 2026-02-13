package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.entity.enums.AppointmentStatus;

import java.util.Date;

public class Appointment implements Cloneable{
    private String id;
    private Patient patient;
    private Doctor doctor;
    private AppointmentStatus status;
    private Date date;

    public Appointment(String id, Doctor doctor, Patient patient, Date date) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.status = AppointmentStatus.PENDING;
    }

    public String getId() { return id; }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public Date getDate() { return date; }

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

    public void setStatus(AppointmentStatus appointmentStatus) {
        this.status = appointmentStatus;
    }
}
