package com.blinkit.backend.config;


import com.blinkit.backend.entity.User;
import com.blinkit.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class AdminSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args)throws Exception{
        boolean adminExists = userRepository.findByUsername("admin").isPresent();
        if(!adminExists){
            User admin= new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(User.Role.ADMIN);

            userRepository.save(admin);
            System.out.println("Admin user created: username='admin', password='admin123'");
        } else {
            System.out.println("Admin user already exists, skipping seeding.");
        }
        }

}
