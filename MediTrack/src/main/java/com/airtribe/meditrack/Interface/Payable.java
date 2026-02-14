package com.airtribe.meditrack.Interface;

public interface Payable {
    double calculateTotal();

    default void printReceipt(double amount) {
        System.out.println("Total Amount to Pay: ₹" + amount);
    }
}
