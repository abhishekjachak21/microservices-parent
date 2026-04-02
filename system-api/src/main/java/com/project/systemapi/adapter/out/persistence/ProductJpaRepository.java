package com.project.systemapi.adapter.out.persistence;


import com.project.systemapi.domain.model.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface ProductJpaRepository
        extends JpaRepository<ProductJpaEntity, Long> {

    Page<ProductJpaEntity> findByStatus(ProductStatus status, Pageable pageable);
    List<ProductJpaEntity> findByCategoryId(Long categoryId);
}
