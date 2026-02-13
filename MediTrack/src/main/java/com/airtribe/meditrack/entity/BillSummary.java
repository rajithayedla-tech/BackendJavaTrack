package com.airtribe.meditrack.entity;

public final class BillSummary {
    private final String patientId;
    private final String doctorId;
    private final double consultationFee;
    private final double medicineCharges;
    private final double totalAmount;

    public BillSummary(String patientId, String doctorId, double consultationFee, double medicineCharges) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.consultationFee = consultationFee;
        this.medicineCharges = medicineCharges;
        this.totalAmount = consultationFee + medicineCharges;
    }

    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public double getConsultationFee() { return consultationFee; }
    public double getMedicineCharges() { return medicineCharges; }
    public double getTotalAmount() { return totalAmount; }

    @Override
    public String toString() {
        return "BillSummary{" +
                "patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", consultationFee=" + consultationFee +
                ", medicineCharges=" + medicineCharges +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
