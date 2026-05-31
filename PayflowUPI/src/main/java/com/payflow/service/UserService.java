package com.payflow.service;

import com.payflow.entity.User;
import com.payflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // @Autowired tells Spring to inject an instance of UserRepository here.
    // At startup, Spring Boot scans for @Repository classes, creates a bean,
    // and wires it into this field automatically (Dependency Injection).
    @Autowired
    private UserRepository userRepository; // Spring injects bean at startup

    public User registerUser(User user) { return userRepository.save(user); }
    public List<User> getAllUsers() { return userRepository.findAll(); }
    public User getUserById(Long id) { return userRepository.findById(id).orElse(null); }
    public User findByUpiId(String upiId) { return userRepository.findByUpiId(upiId); }
    public List<User> findUsersWithBalanceAbove(Double amount) { return userRepository.findUsersWithBalanceAbove(amount); }

}
