package io.github.nosql.model;

public class DataResponse {
    private final String id;
    private final String key;
    private final byte[] value;
    private final long timestamp;

    public DataResponse(String id, String key, byte[] value) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public byte[] getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
