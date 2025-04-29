package com.example.onetimesecret.repository;

import com.example.onetimesecret.model.Secret;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretRepository extends JpaRepository<Secret, String> {
}
