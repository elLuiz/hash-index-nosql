package io.github.nosql.infrastructure;

import io.github.nosql.application.StorageRepository;
import io.github.nosql.model.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

class HashMapStorageRepositoryTest {
    private StorageRepository storageRepository;

    @BeforeEach
    void setUp() {
        this.storageRepository = new HashMapStorageRepository();
    }

    @Test
    void shouldPersistDataIntoTheHashMap() {
        Data data = new Data("fpp".getBytes(StandardCharsets.UTF_8));
        storageRepository.persist("foo", data);

        Assertions.assertEquals(data, storageRepository.findByKey("foo").orElse(null));
    }
}