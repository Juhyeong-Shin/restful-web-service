package com.example.restfulwebservice.halloween;

public class MonsterNotFoundException extends RuntimeException {
    public MonsterNotFoundException(String message) {
    super(message);
    }
}
