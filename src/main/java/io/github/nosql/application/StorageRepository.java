package io.github.nosql.application;

import io.github.nosql.exception.KeyAlreadyExists;
import io.github.nosql.exception.NoResultException;
import io.github.nosql.model.Data;

import java.util.Optional;

public interface StorageRepository {
    /**
     * Inserts a new value into the database if the given key does not exist.
     *
     * @param key
     * @param data The data to be persisted.
     * @throws KeyAlreadyExists If the given key is already present in the storage.
     */
    void persist(String key, Data data) throws KeyAlreadyExists;

    /**
     * Retrieves the value associated with a given key.
     * @param key The key of the data.
     * @return Optional of the data.
     * @throws NoResultException if no data is associated with the given key.
     */
    Optional<Data> findByKey(String key) throws NoResultException;
}