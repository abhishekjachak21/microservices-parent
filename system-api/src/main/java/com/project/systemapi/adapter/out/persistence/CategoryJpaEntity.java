package com.project.systemapi.adapter.out.persistence;

import jakarta.persistence.*;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "categories",
        indexes = {
                @Index(name = "idx_categories_name", columnList = "name")
        }
)
public class CategoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private java.time.LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.createdAt = this.updatedAt = java.time.LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = java.time.LocalDateTime.now();
    }
}