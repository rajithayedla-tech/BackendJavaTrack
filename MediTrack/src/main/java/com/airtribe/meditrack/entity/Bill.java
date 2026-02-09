package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.Interface.Payable;
import com.airtribe.meditrack.constants.Constants;

public class Bill implements Payable {
    private Doctor doctor;

    public Bill(Doctor doctor) {
        this.doctor = doctor;
    }

    public Bill() {

    }

    @Override
    public double calculateTotal() {
        double baseFee = doctor.getConsultationFee();
        return baseFee + (baseFee * Constants.TAX_RATE);
    }
}
