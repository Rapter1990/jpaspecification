package com.springboot.jpaspecification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.util.Date;

/**
 * Base entity class named {@link BaseEntity} serving as a common superclass for JPA entities.
 * Provides fields for tracking creation and update timestamps.
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * Timestamp indicating the creation date of the entity.
     */
    @Column(name = "CREATED_AT")
    protected Date createdAt;

    /**
     * Timestamp indicating the last update date of the entity.
     */
    @Column(name = "UPDATED_AT")
    protected Date updatedAt;

    /**
     * Sets the creation timestamp before persisting a new entity.
     */
    @PrePersist
    private void prePersist() {
        this.createdAt = new Date();
    }

    /**
     * Sets the update timestamp before updating an existing entity.
     */
    @PreUpdate
    private void preUpdate() {
        this.updatedAt = new Date();
    }

}
