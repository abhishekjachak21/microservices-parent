package com.project.systemapi.adapter.out.persistence;

import com.project.systemapi.application.port.out.LoadProductPort;
import com.project.systemapi.application.port.out.SaveProductPort;
import com.project.systemapi.domain.model.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceAdapter
        implements SaveProductPort, LoadProductPort {

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

        return mapToDomain(saved);
    }

    @Override
    public Product getById(Long id) {
        return repo.findById(id)
                .map(this::mapToDomain)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private Product mapToDomain(ProductJpaEntity e) {
        return new Product(
                e.getId(),
                e.getName(),
                e.getPrice(),
                e.getStatus(),
                null,
                null,
                e.getCreatedAt()
        );
    }
}