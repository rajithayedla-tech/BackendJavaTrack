package com.airtribe.meditrack.entity;

public final class BillSummary {

    private final String doctorName;
    private final double totalAmount;

    public BillSummary(String doctorName, double totalAmount) {
        this.doctorName = doctorName;
        this.totalAmount = totalAmount;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
