package com.airtribe.meditrack.util;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.enums.Specialization;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public final class AIHelper {

    private static final Map<String, Specialization> SYMPTOM_MAP = new HashMap<>();
    private static final Map<String, Integer> SEVERITY_MAP = new HashMap<>();

    static {
        // Symptom → Specialization
        SYMPTOM_MAP.put("chest pain", Specialization.CARDIOLOGY);
        SYMPTOM_MAP.put("heart", Specialization.CARDIOLOGY);
        SYMPTOM_MAP.put("skin", Specialization.DERMATOLOGY);
        SYMPTOM_MAP.put("rash", Specialization.DERMATOLOGY);
        SYMPTOM_MAP.put("fever", Specialization.GENERAL);
        SYMPTOM_MAP.put("headache", Specialization.GENERAL);

        // Symptom → Severity Score (1-5)
        SEVERITY_MAP.put("chest pain", 5);
        SEVERITY_MAP.put("heart", 5);
        SEVERITY_MAP.put("rash", 2);
        SEVERITY_MAP.put("skin", 2);
        SEVERITY_MAP.put("fever", 3);
        SEVERITY_MAP.put("headache", 2);
    }

    private AIHelper() {}

    /* =====================================================
       Doctor Recommendation with Confidence Score
       ===================================================== */

    public static List<DoctorRecommendation> recommendDoctors(
            String symptoms,
            Collection<Doctor> doctors) {

        if (symptoms == null || symptoms.isBlank()) {
            return Collections.emptyList();
        }

        String lower = symptoms.toLowerCase();
        Map<Specialization, Integer> matchScore = new HashMap<>();

        for (Map.Entry<String, Specialization> entry : SYMPTOM_MAP.entrySet()) {
            if (lower.contains(entry.getKey())) {
                matchScore.merge(entry.getValue(), 1, Integer::sum);
            }
        }

        if (matchScore.isEmpty()) {
            matchScore.put(Specialization.GENERAL, 1);
        }

        return doctors.stream()
                .filter(d -> matchScore.containsKey(d.getSpecialization()))
                .map(d -> new DoctorRecommendation(
                        d,
                        calculateConfidence(matchScore.get(d.getSpecialization()))
                ))
                .sorted(Comparator.comparingDouble(DoctorRecommendation::getConfidence).reversed())
                .collect(Collectors.toList());
    }

    private static double calculateConfidence(int matchCount) {
        return Math.min(1.0, matchCount * 0.5);
    }

    /* =====================================================
       Smart Slot Suggestion with Urgency Awareness
       ===================================================== */

    public static List<LocalDateTime> suggestSlots(String symptoms) {

        int severity = inferSeverity(symptoms);
        List<LocalDateTime> slots = new ArrayList<>();

        LocalDateTime start = severity >= 4
                ? LocalDateTime.now().plusMinutes(30)
                : LocalDateTime.now().plusHours(2);

        for (int i = 0; i < 5; i++) {
            LocalDateTime candidate = start.plusHours(i);

            // Avoid Sunday
            if (candidate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                slots.add(candidate);
            }
        }

        return slots;
    }

    private static int inferSeverity(String symptoms) {
        String lower = symptoms.toLowerCase();
        return SEVERITY_MAP.entrySet()
                .stream()
                .filter(e -> lower.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .max(Integer::compare)
                .orElse(1);
    }

    /* =====================================================
       Explainability Feature (Interview Booster)
       ===================================================== */

    public static String explainRecommendation(String symptoms) {
        String lower = symptoms.toLowerCase();

        return SYMPTOM_MAP.entrySet()
                .stream()
                .filter(e -> lower.contains(e.getKey()))
                .map(e -> "Matched symptom '" + e.getKey() +
                        "' → Recommended " + e.getValue())
                .findFirst()
                .orElse("No direct match found. Defaulted to GENERAL.");
    }

    /* =====================================================
       DTO for Recommendation Result
       ===================================================== */

    public static class DoctorRecommendation {

        private final Doctor doctor;
        private final double confidence;

        public DoctorRecommendation(Doctor doctor, double confidence) {
            this.doctor = doctor;
            this.confidence = confidence;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public double getConfidence() {
            return confidence;
        }

        @Override
        public String toString() {
            return doctor.getName() +
                    " | Specialization: " + doctor.getSpecialization() +
                    " | Confidence: " + (confidence * 100) + "%";
        }
    }
}
