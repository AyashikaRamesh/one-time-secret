package com.example.onetimesecret.controller;

import com.example.onetimesecret.model.Secret;
import com.example.onetimesecret.service.SecretService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/secrets")
@CrossOrigin(origins = "*")
public class SecretController {
    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createSecret(@RequestBody Map<String, String> request) {
        System.out.println("Received request: " + request);
        String content = request.get("content");
        String password = request.get("password");
        
        System.out.println("Content: " + content);
        System.out.println("Password: " + password);
        
        if (content == null || content.isEmpty()) {
            System.out.println("Content is null or empty");
            return ResponseEntity.badRequest().build();
        }
        
        try {
            Secret secret = secretService.createSecret(content, password);
            System.out.println("Created secret with ID: " + secret.getId());
            return ResponseEntity.ok(Map.of("id", secret.getId()));
        } catch (Exception e) {
            System.err.println("Error creating secret: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}/check")
    public ResponseEntity<Map<String, Boolean>> checkSecret(@PathVariable String id) {
        if (!secretService.checkSecretExists(id)) {
            return ResponseEntity.notFound().build();
        }
        
        boolean requiresPassword = secretService.requiresPassword(id);
        return ResponseEntity.ok(Map.of("requiresPassword", requiresPassword));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Map<String, String>> getSecret(
            @PathVariable String id,
            @RequestBody(required = false) Map<String, String> request) {
        
        String password = request != null ? request.get("password") : null;
        Secret secret = secretService.getSecret(id, password);
        
        if (secret == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(Map.of("content", secret.getContent()));
    }
}
