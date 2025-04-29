package com.example.onetimesecret.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
public class Secret {
    @Id
    private String id;
    private String content;
    private String passwordHash;
    private LocalDateTime createdAt;
    private boolean viewed = false;

    public Secret() {}

    public Secret(String id, String content, String passwordHash, LocalDateTime createdAt, boolean viewed) {
        this.id = id;
        this.content = content;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.viewed = viewed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
}
