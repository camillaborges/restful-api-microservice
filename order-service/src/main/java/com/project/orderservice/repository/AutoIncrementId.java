package com.project.orderservice.repository;

/**
 * Class used to generate the next "Auto Incremented" sequential ID for the Repository Services.
 */
public class AutoIncrementId {
    private Long currentAutoIncrementId = 0L;

    protected Long getNextAutoIncrementId() {
        return ++currentAutoIncrementId;
    }

}