package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.entity.enums.Specialization;

public class Doctor extends Person {
    private Specialization specialization;
    private double consultationFee;

    public Doctor(String id, String name, int age,
                  Specialization specialization, double consultationFee) {
        super(id, name, age);
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    @Override
    public String getSummary() {
        return "Doctor: " + name + " | " + specialization + " | Fee: " + consultationFee;
    }
}
