package com.example.repository;


import com.example.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth,String> {
    Optional<UserAuth> findUserByUsername(String username);
}
