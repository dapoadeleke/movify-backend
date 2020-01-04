package com.movify.model;

import com.movify.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter @Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String fullName;
    String email;
    String passwordHash;
    int failedLoginCount;
    LocalDateTime lastLoggedIn;
    @Enumerated(EnumType.STRING)
    Status status;
    LocalDateTime dateCreated;
}
