package com.payflow.service;

import com.payflow.entity.Transaction;
import com.payflow.entity.User;
import com.payflow.repository.TransactionRepository;
import com.payflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    // Spring Boot scans for @Repository classes at startup,
    // creates beans for them, and injects them here automatically.
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Transaction sendMoney(Transaction transaction) {
        // 1. Find sender and receiver by UPI ID
        User sender = userRepository.findByUpiId(transaction.getSenderUpiId());
        User receiver = userRepository.findByUpiId(transaction.getReceiverUpiId());

        if (sender == null || receiver == null) {
            throw new RuntimeException("Invalid UPI IDs provided.");
        }

        // 2. Update balances
        if (sender.getBalance() < transaction.getAmount()) {
            throw new RuntimeException("Insufficient balance.");
        }

        sender.setBalance(sender.getBalance() - transaction.getAmount());
        receiver.setBalance(receiver.getBalance() + transaction.getAmount());

        // 3. Save updated users
        userRepository.save(sender);
        userRepository.save(receiver);

        // 4. Save transaction record
        return transactionRepository.save(transaction);
    }
}
