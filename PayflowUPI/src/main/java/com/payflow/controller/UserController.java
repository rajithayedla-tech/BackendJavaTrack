package com.payflow.controller;

import com.payflow.entity.User;
import com.payflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) { return userService.registerUser(user); }

    @GetMapping
    public List<User> getAllUsers() { return userService.getAllUsers(); }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) { return userService.getUserById(id); }

    @GetMapping("/upi/{upiId}")
    public User getUserByUpiId(@PathVariable String upiId) { return userService.findByUpiId(upiId); }

    @GetMapping("/balance/{amount}")
    public List<User> getUsersWithBalanceAbove(@PathVariable Double amount) { return userService.findUsersWithBalanceAbove(amount); }

}

