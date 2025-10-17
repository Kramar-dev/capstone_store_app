package com.gd.storeapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(nullable = false, updatable = false, unique = true, length = 36)
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    private String description;

    @Positive
    @Column(nullable = false)
    private Double price;

    @PositiveOrZero
    @Column(nullable = false)
    private int quantity;

    @PrePersist
    private void assignUuid() {
        if (this.id == null) this.id = UUID.randomUUID().toString();
    }
}

