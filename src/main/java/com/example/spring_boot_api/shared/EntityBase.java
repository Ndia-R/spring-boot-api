package com.example.spring_boot_api.shared;

import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public class EntityBase {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDeleted = false;

    @PrePersist
    protected void prePersist() {
        if (Objects.isNull(this.createdAt))
            this.createdAt = LocalDateTime.now();
        if (Objects.isNull(this.updatedAt))
            this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        if (Objects.isNull(this.updatedAt))
            this.updatedAt = LocalDateTime.now();
    }
}
