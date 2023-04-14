package io.github.nosql.model;

import java.util.UUID;

public class Data {
    private final String id;
    private final byte[] value;
    private final long createdAt;

    public Data(byte[] value) {
        this.id = UUID.randomUUID().toString();
        this.value = value;
        this.createdAt = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public byte[] getValue() {
        return value;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}