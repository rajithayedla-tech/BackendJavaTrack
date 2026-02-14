package com.airtribe.meditrack.entity;

public abstract class Person extends MedicalEntity {

    protected String id;
    protected String name;
    protected int age;

    protected Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract String getSummary();
}
