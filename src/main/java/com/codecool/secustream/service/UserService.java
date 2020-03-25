package com.codecool.secustream.service;

import com.codecool.secustream.model.Role;
import com.codecool.secustream.model.SecuUser;
import com.codecool.secustream.repository.SecuUserRepository;
import com.codecool.secustream.security.UserCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SecuUserRepository secuUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecuUser register(String username, String password, Set<Role> roles) {
        return secuUserRepository.save(
                SecuUser.builder()
                        .userName(username)
                        .hashedPassword(bCryptPasswordEncoder.encode(password))
                        .roles(roles)
                        .build()
        );
    }

    public SecuUser register(String username, String password) {
        return secuUserRepository.save(
                SecuUser.builder()
                        .userName(username)
                        .hashedPassword(bCryptPasswordEncoder.encode(password))
                        .role(Role.USER)
                        .build()
        );
    }

    public SecuUser register(UserCredentials userCredentials) {
        return register(userCredentials.getUsername(), userCredentials.getPassword());
    }

}
