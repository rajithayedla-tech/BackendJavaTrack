package com.airtribe.meditrack.factory;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.strategy.GSTTaxStrategy;
import com.airtribe.meditrack.strategy.TaxStrategy;

public class BillFactory {

    public static Bill createBill(String type, Doctor doctor) {

        TaxStrategy strategy;

        switch (type) {
            case "CONSULTATION":
                strategy = new GSTTaxStrategy();
                break;
            default:
                throw new IllegalArgumentException("Invalid bill type");
        }

        return new Bill(doctor, strategy);
    }
}
