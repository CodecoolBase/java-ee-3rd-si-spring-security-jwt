package com.codecool.secustream.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
public class SecuUser {

    @Id
    private String userName;

    @NotBlank
    private String hashedPassword;

    @ElementCollection
    @Singular
    @NotEmpty
    private Set<String> roles;
}
