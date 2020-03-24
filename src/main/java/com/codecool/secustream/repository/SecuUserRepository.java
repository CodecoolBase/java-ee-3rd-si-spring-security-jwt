package com.codecool.secustream.repository;

import com.codecool.secustream.model.SecuUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecuUserRepository extends JpaRepository<SecuUser, String> {
}
