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
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());

        ProductJpaEntity saved = repo.save(entity);

        return new Product(
                saved.getId(),
                saved.getName(),
                saved.getPrice()
        );
    }
}
