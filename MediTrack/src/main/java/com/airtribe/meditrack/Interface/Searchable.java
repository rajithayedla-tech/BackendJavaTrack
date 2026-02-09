package com.airtribe.meditrack.Interface;

public interface Searchable<T> {
    T searchById(String id);

    default void notFoundMessage() {
        System.out.println("Record not found");
    }
}
