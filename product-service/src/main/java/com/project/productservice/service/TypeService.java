package com.project.productservice.service;

import com.project.productservice.controller.request.AddTypeRequest;
import com.project.productservice.controller.request.UpdateTypeRequest;
import com.project.productservice.model.Type;
import com.project.productservice.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type addType(AddTypeRequest request) {
        var newType = new Type(request.name());
        return typeRepository.addType(newType);
    }

    public Type findById(Long id) {
        return typeRepository.findById(id);
    }

    public void deleteById(Long id) {
        typeRepository.deleteById(id);
    }

    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    public Type updateType(Long id, UpdateTypeRequest request) {
        return typeRepository.updateType(id, request);
    }
}
