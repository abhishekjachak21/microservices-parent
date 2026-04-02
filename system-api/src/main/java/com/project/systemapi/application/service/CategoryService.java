package com.project.systemapi.application.service;

import com.project.systemapi.adapter.out.persistence.CategoryJpaEntity;
import com.project.systemapi.adapter.out.persistence.CategoryJpaRepository;
import com.project.systemapi.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryJpaRepository repository;

    public Category create(String name) {
        CategoryJpaEntity entity = new CategoryJpaEntity();
        entity.setName(name);

        CategoryJpaEntity saved = repository.save(entity);

        return new Category(saved.getId(), saved.getName());
    }

    public List<Category> getAll() {
        return repository.findAll().stream()
                .map(e -> new Category(e.getId(), e.getName()))
                .toList();
    }
}