package com.project.productservice.repository;

import com.project.productservice.controller.request.UpdateTypeRequest;
import com.project.productservice.exception.EntityNotFoundException;
import com.project.productservice.model.Type;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TypeRepository extends AutoIncrementId {

    private static final Logger logger = LoggerFactory.getLogger(TypeRepository.class);

    private final Map<Long, Type> catalogTypes = new HashMap<>();

    @PostConstruct
    public void persistInitialTypeData() {
        logger.info("Initializing dummy Type data...");
        catalogTypes.put(1L, new Type(1L, "PC Game"));
        catalogTypes.put(2L, new Type(2L, "Sony"));
        catalogTypes.put(3L, new Type(3L, "Microsoft"));

        // inform the last hardcoded ID (3) to the AutoIncrementId class, to keep track.
        setCurrentAutoIncrementId(3L);
    }

    public Type addType(Type type) {
        logger.info("Add new Type to catalog: {}", type);
        // Set next sequential ID
        type.setId(getNextAutoIncrementId());
        catalogTypes.put(type.getId(), type);
        return type;
    }

    public Type findById(Long id) {
        logger.info("Find type on catalog by ID: {}", id);
        return catalogTypes.entrySet().stream()
                .filter(typeMap -> typeMap.getKey().equals(id))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new EntityNotFoundException("Type was not found."));
    }

    public void deleteById(Long id) {
        logger.info("Delete type on catalog by ID: {}", id);
        catalogTypes.remove(id);
    }

    public List<Type> findAll() {
        return catalogTypes.values().stream().toList();
    }

    public Type updateType(Long id, UpdateTypeRequest request) {
        var targetType = findById(id);
        targetType.setName(request.name());
        return targetType;
    }
}
