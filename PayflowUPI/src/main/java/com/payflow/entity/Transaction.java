package com.payflow.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;   // 👈 renamed from id

    private String senderUpiId;
    private String receiverUpiId;
    private Double amount;
    private String note;          // 👈 new field

    // ✅ getters and setters
    public Long getTransactionId() { return transactionId; }
    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }

    public String getSenderUpiId() { return senderUpiId; }
    public void setSenderUpiId(String senderUpiId) { this.senderUpiId = senderUpiId; }

    public String getReceiverUpiId() { return receiverUpiId; }
    public void setReceiverUpiId(String receiverUpiId) { this.receiverUpiId = receiverUpiId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
