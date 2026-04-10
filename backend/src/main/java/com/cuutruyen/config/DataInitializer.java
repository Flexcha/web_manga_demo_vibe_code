package com.cuutruyen.config;

import com.cuutruyen.entity.User;
import com.cuutruyen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        log.info("Checking and initializing sample accounts...");
        
        createUserIfNotExist("admin", "admin@cuutruyen.com", "admin123", User.Role.admin);
        createUserIfNotExist("translator", "trans@cuutruyen.com", "trans123", User.Role.translator);
        createUserIfNotExist("uploader", "upload@cuutruyen.com", "upload123", User.Role.uploader);
        createUserIfNotExist("user", "user@cuutruyen.com", "user123", User.Role.user);
        
        log.info("Initialization complete.");
    }

    private void createUserIfNotExist(String username, String email, String password, User.Role role) {
        if (!userRepository.existsByUsername(username)) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPasswordHash(passwordEncoder.encode(password));
            user.setRole(role);
            user.setDisplayName(username.toUpperCase());
            userRepository.save(user);
            log.info("Created user: {}", username);
        }
    }
}
