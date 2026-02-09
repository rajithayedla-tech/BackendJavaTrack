package com.airtribe.meditrack.entity;

public class Patient extends Person implements Cloneable{
    public Patient(String id, String name, int age) {
        super(id, name, age);
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
