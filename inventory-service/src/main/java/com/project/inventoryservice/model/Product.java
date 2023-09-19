package com.project.inventoryservice.model;

public record Product(Long id, String name, String description, Type type) {
}
