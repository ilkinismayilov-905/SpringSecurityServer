package com.example.repository;


import com.example.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTokenRepository
        extends JpaRepository<Token, Integer> {
    Optional<Token> findTokenByIdentifier(String identifier);
}
