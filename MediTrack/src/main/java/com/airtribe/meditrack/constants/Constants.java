package com.airtribe.meditrack.constants;

public class Constants {
    // ===== Billing =====
    public static final double TAX_RATE = 0.18;

    // ===== Application Messages =====
    public static final String APP_NAME = "MediTrack – Clinic & Appointment Management System";

    // ===== File Paths (for future CSV / persistence feature) =====
    public static final String PATIENT_CSV_PATH = "data/patients.csv";
    public static final String DOCTOR_CSV_PATH = "data/doctors.csv";
    public static final String APPOINTMENT_CSV_PATH = "data/appointments.csv";

    // ===== Static Initialization Block =====
    static {
        System.out.println("Constants loaded for MediTrack");
    }

    public Constants() {
    }
}
