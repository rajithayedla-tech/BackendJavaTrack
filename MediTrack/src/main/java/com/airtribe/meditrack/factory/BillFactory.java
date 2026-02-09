package com.airtribe.meditrack.factory;

import com.airtribe.meditrack.entity.Bill;

public class BillFactory {

    public static Bill createBill(String type) {
        return switch (type) {
            case "CONSULTATION" -> new Bill();
            default -> throw new IllegalArgumentException("Invalid bill type");
        };
    }
}
