package io.github.nosql.exception;

public class KeyAlreadyExists extends RuntimeException {
    public KeyAlreadyExists(String message) {
        super(message);
    }
}