package com.codecool.secustream.service;

import com.codecool.secustream.controller.dto.UserCredentials;
import com.codecool.secustream.model.Role;
import com.codecool.secustream.model.SecuUser;
import com.codecool.secustream.repository.SecuUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SecuUserRepository secuUserRepository;
    private final PasswordEncoder encoder;

    public SecuUser register(String username, String password, Set<Role> roles) {
        return secuUserRepository.save(
                SecuUser.builder()
                        .userName(username)
                        .hashedPassword(encoder.encode(password))
                        .roles(roles)
                        .build()
        );
    }

    public SecuUser register(String username, String password) {
        return secuUserRepository.save(
                SecuUser.builder()
                        .userName(username)
                        .hashedPassword(encoder.encode(password))
                        .role(Role.USER)
                        .build()
        );
    }

    public SecuUser register(UserCredentials userCredentials) {
        return register(userCredentials.getUsername(), userCredentials.getPassword());
    }

}
