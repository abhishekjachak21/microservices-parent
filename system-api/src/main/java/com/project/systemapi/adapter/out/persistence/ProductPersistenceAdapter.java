package com.project.systemapi.adapter.out.persistence;

import com.project.systemapi.application.port.out.SaveProductPort;
import com.project.systemapi.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceAdapter implements SaveProductPort {

    private final ProductJpaRepository repo;

    public ProductPersistenceAdapter(ProductJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product save(Product product) {

        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setName(product.name());
        entity.setPrice(product.price());
        entity.setStatus(product.status());

        ProductJpaEntity saved = repo.save(entity);

        return new Product(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getStatus(),
                null,
                null,
                saved.getCreatedAt()
        );
    }
}