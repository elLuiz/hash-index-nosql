package io.github.nosql.infrastructure;

import io.github.nosql.application.StorageRepository;
import io.github.nosql.exception.KeyAlreadyExists;
import io.github.nosql.exception.NoResultException;
import io.github.nosql.model.Data;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class HashMapStorageRepository implements StorageRepository {
    private final Map<String, Data> storage = new ConcurrentHashMap<>();

    @Override
    public void persist(String key, Data data) throws KeyAlreadyExists {
        if (storage.containsKey(key)) {
            throw new KeyAlreadyExists("Key already exists");
        }
        this.storage.put(key, data);
    }

    @Override
    public Optional<Data> findByKey(String key) throws NoResultException {
        return Optional.ofNullable(storage.getOrDefault(key, null));
    }
}