package com.subash.clinic.controller;

import com.subash.clinic.model.User;
import com.subash.clinic.repository.UserRepository;
import com.subash.clinic.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return Map.of("message", "User registered successfully");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        String token = jwtUtil.generateToken(user.getUsername());
        return Map.of("token", token);
    }
}
