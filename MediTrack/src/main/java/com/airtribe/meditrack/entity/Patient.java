package com.airtribe.meditrack.entity;

public class Patient extends Person implements Cloneable{

    private static int totalPatients;

    static {
        totalPatients = 0;
        System.out.println("Patient class loaded into memory.");
    }

    public Patient(String id, String name, int age) {
        super(id, name, age);
        totalPatients++;
    }

    public static int getTotalPatients() {
        return totalPatients;
    }



    @Override
    public Patient clone() {
        try {
            return (Patient) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    @Override
    public String getSummary() {
        return "Patient: " + name + " | Age: " + age;
    }
}
