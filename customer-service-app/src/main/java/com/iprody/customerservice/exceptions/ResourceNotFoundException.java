package com.iprody.customerservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s with id - %s not found", resourceName, id));
    }
}
