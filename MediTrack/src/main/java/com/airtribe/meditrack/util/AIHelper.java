package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.enums.Specialization;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * AIHelper provides rule-based recommendations.
 * This is NOT ML-based; it uses deterministic rules,
 * which is appropriate for a Java OOP capstone.
 */
public final class AIHelper {

    // Symptom → Specialization mapping (rule engine)
    private static final Map<String, Specialization> SYMPTOM_MAP = new HashMap<>();

    static {
        SYMPTOM_MAP.put("chest pain", Specialization.CARDIOLOGY);
        SYMPTOM_MAP.put("heart", Specialization.CARDIOLOGY);
        SYMPTOM_MAP.put("skin", Specialization.DERMATOLOGY);
        SYMPTOM_MAP.put("rash", Specialization.DERMATOLOGY);
    }

    private AIHelper() {
        // utility class
    }

    /* =========================
       Doctor Recommendation
       ========================= */

    public static List<Doctor> recommendDoctors(
            String symptoms,
            Collection<Doctor> doctors) {

        if (symptoms == null || symptoms.isBlank()) {
            return Collections.emptyList();
        }

        Specialization specialization = inferSpecialization(symptoms);

        return doctors.stream()
                .filter(d -> d.getSpecialization() == specialization)
                .collect(Collectors.toList());
    }

    private static Specialization inferSpecialization(String symptoms) {
        String lower = symptoms.toLowerCase();

        return SYMPTOM_MAP.entrySet()
                .stream()
                .filter(e -> lower.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(Specialization.GENERAL);
    }

    /* =========================
       Appointment Slot Suggestion
       ========================= */

    public static List<LocalDateTime> suggestSlots() {
        List<LocalDateTime> slots = new ArrayList<>();
        LocalDateTime start = LocalDateTime.now().plusHours(1);

        for (int i = 0; i < 5; i++) {
            slots.add(start.plusHours(i));
        }
        return slots;
    }
}
