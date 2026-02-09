package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static final IdGenerator INSTANCE = new IdGenerator();

    private final AtomicInteger counter = new AtomicInteger(1000);

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String generateId(String prefix) {
        return prefix + counter.incrementAndGet();
    }
}
