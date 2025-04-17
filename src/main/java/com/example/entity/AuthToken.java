package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AuthToken {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

}
