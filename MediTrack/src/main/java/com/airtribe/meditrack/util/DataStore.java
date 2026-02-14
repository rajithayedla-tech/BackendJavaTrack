package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DataStore<T> {
    private List<T> items = new ArrayList<>();

    // Add a new item
    public void add(T item) {
        items.add(item);
    }

    // Get all items (defensive copy)
    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    // Find a single item by condition
    public T find(Predicate<T> condition) {
        return items.stream()
                .filter(condition)
                .findFirst()
                .orElse(null);
    }

    // Search multiple items by condition
    public List<T> search(Predicate<T> condition) {
        return items.stream()
                .filter(condition)
                .toList(); // Java 16+, use collect(Collectors.toList()) for Java 8
    }

    // Check if empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Clear all items
    public void clear() {
        items.clear();
    }
}
