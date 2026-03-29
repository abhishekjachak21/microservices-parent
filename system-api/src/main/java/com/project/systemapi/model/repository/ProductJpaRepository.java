package com.project.systemapi.model.repository;


import com.project.systemapi.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository
        extends JpaRepository<ProductJpaEntity, UUID> {
}
