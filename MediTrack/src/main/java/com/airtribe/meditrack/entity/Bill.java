package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.Interface.Payable;
import com.airtribe.meditrack.strategy.TaxStrategy;

public class Bill implements Payable {

    private Doctor doctor;
    private TaxStrategy taxStrategy;

    public Bill(Doctor doctor, TaxStrategy taxStrategy) {
        this.doctor = doctor;
        this.taxStrategy = taxStrategy;
    }

    @Override
    public double calculateTotal() {
        double baseFee = doctor.getConsultationFee();
        double tax = taxStrategy.calculateTax(baseFee);
        return baseFee + tax;
    }
}
