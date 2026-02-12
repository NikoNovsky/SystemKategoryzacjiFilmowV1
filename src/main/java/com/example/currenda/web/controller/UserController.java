package com.example.currenda.web.controller;

import com.example.currenda.data.model.Authority;
import com.example.currenda.data.model.User;
import com.example.currenda.data.repository.AuthorityRepository;
import com.example.currenda.data.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @GetMapping()
    public String getHome(Model model) {
        return "register";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(User user) {
        if (!this.userRepository.existsById(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            this.userRepository.save(user);
            Authority authority = new Authority();
            authority.setUsername(user.getUsername());
            authority.setAuthority("ROLE_USER");
            this.authorityRepository.save(authority);
            return "register";
        } else {
            throw new RuntimeException("User already exists");
        }
    }
}
