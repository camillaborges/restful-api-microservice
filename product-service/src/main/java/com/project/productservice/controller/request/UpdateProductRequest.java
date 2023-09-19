package com.project.productservice.controller.request;

public record UpdateProductRequest(String name, String description, Long typeId) {
}
