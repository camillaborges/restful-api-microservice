package com.project.productservice.controller;

import com.project.productservice.controller.request.AddTypeRequest;
import com.project.productservice.controller.request.UpdateTypeRequest;
import com.project.productservice.model.Type;
import com.project.productservice.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    private final TypeService service;

    @Autowired
    public TypeController(TypeService service) {
        this.service = service;
    }

    @PostMapping
    public Type addType(@RequestBody AddTypeRequest type) {
        return service.addType(type);
    }

    @GetMapping("/{id}")
    public Type findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping
    public List<Type> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public Type updateType(@PathVariable Long id, @RequestBody UpdateTypeRequest request) {
        return service.updateType(id, request);
    }

}
