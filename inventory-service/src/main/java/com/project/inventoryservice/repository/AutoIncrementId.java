package com.project.inventoryservice.repository;

/**
 * Class used to generate the next "Auto Incremented" sequential ID for the Repository Services.
 */
public class AutoIncrementId {
    private Long currentAutoIncrementId = 0L;

    protected void setCurrentAutoIncrementId(Long currentAutoIncrementId) {
        this.currentAutoIncrementId = currentAutoIncrementId;
    }

    protected Long getNextAutoIncrementId() {
        return ++currentAutoIncrementId;
    }

}
