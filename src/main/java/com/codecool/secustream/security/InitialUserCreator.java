package com.codecool.secustream.security;

import com.codecool.secustream.model.Role;
import com.codecool.secustream.model.SecuUser;
import com.codecool.secustream.repository.SecuUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class InitialUserCreator {

    private final SecuUserRepository secuUserRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Bean
    public CommandLineRunner createUsers() {
        return args -> {
            secuUserRepository.save(
                    SecuUser.builder()
                            .userName("admin")
                            .hashedPassword(bCryptPasswordEncoder.encode("admin"))
                            .role(Role.ADMIN)
                            .role(Role.USER)
                            .build()
            );

            secuUserRepository.save(
                    SecuUser.builder()
                            .userName("user")
                            .hashedPassword(bCryptPasswordEncoder.encode("user"))
                            .role(Role.USER)
                            .build()
            );
        };
    }
}
