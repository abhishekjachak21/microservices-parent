package com.project.systemapi.adapter.out.persistence;

import jakarta.persistence.*;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}