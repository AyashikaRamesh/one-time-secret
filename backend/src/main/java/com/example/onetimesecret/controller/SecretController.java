package com.example.onetimesecret.controller;

import com.example.onetimesecret.model.Secret;
import com.example.onetimesecret.service.SecretService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/secrets")
public class SecretController {
    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createSecret(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        String password = request.get("password");
        
        if (content == null || content.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        Secret secret = secretService.createSecret(content, password);
        return ResponseEntity.ok(Map.of("id", secret.getId()));
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
