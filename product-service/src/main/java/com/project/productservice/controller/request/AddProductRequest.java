package com.project.productservice.controller.request;


public record AddProductRequest(String name, String description, Long typeId) {
}
