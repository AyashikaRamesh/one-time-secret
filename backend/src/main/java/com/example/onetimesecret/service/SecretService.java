package com.example.onetimesecret.service;

import com.example.onetimesecret.model.Secret;
import com.example.onetimesecret.repository.SecretRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;

@Service
public class SecretService {
    private final SecretRepository secretRepository;
    private final PasswordEncoder passwordEncoder;

    public SecretService(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Secret createSecret(String content, String password) {
        Secret secret = new Secret();
        secret.setId(UUID.randomUUID().toString());
        secret.setContent(content);
        secret.setCreatedAt(LocalDateTime.now());
        secret.setViewed(false);
        
        if (password != null && !password.isEmpty()) {
            secret.setPasswordHash(passwordEncoder.encode(password));
        }
        
        return secretRepository.save(secret);
    }

    public boolean checkSecretExists(String id) {
        return secretRepository.findById(id)
                .map(secret -> !secret.isViewed())
                .orElse(false);
    }

    public boolean requiresPassword(String id) {
        return secretRepository.findById(id)
                .map(secret -> secret.getPasswordHash() != null)
                .orElse(false);
    }

    public Secret getSecret(String id, String password) {
        return secretRepository.findById(id)
                .filter(secret -> !secret.isViewed())
                .filter(secret -> {
                    if (secret.getPasswordHash() != null) {
                        return password != null && passwordEncoder.matches(password, secret.getPasswordHash());
                    }
                    return true;
                })
                .map(secret -> {
                    secret.setViewed(true);
                    return secretRepository.save(secret);
                })
                .orElse(null);
    }
}
