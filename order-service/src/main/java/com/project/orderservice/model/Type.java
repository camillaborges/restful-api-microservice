package com.project.orderservice.model;

// TODO: Find a way to reuse the Models (Entities) between different microservice,
// currently, all models are duplicated...
public record Type(Long id, String name) {
}
