package com.airtribe.meditrack.strategy;

public class GSTTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(double amount) {
        return amount * 0.18;
    }
}
