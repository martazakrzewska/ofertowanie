package com.mazak.ofertowanie.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ResponseFactory {
    public static <T> ResponseEntity<T> created(T createdObject) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createdObject);
    }

    public static <T> ResponseEntity<T> badRequest() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public static <T> ResponseEntity<T> ok(T value) {
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }
}
