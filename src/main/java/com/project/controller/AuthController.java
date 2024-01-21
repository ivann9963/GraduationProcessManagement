package com.project.controller;

import com.project.dto.RegistrationDto;
import com.project.dto.LoginDto;
import com.project.dto.Role;
import com.project.entity.Student;
import com.project.entity.Teacher;
import com.project.entity.User;
import com.project.repository.UserRepository;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto registrationDto) {
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        User newUser;
        try {
            Role role = EnumUtils.getEnumIgnoreCase(Role.class, registrationDto.getRole());

            if (role == null) {
                return ResponseEntity.badRequest().body("Invalid user type");
            }

            switch (role) {
                case TEACHER:
                    newUser = new Teacher();
                    break;
                case STUDENT:
                    newUser = new Student();
                    break;
                default:
                    return ResponseEntity.badRequest().body("Role should be provided");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid user type");
        }

        newUser.setEnabled(true);
        newUser.setUsername(registrationDto.getUsername());
        newUser.setPassword(encodeBase64(registrationDto.getPassword())); // Encode password using BCrypt
        userRepository.save(newUser);
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        if (userRepository.findByUsername(loginDto.getUsername()).isEmpty()) {
            return ResponseEntity.badRequest().body("User does not exist");
        }
        User user = userRepository.findByUsername(loginDto.getUsername()).get();

//        UsernamePasswordAuthenticationToken authRequest =
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
//        Authentication authentication = authenticationManager.authenticate(authRequest);
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (decodeBase64(user.getPassword()).equals(loginDto.getPassword())) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
//        }
    }


    public String encodeBase64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
    private String decodeBase64(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return new String(decodedBytes);
    }
}