package com.inetum.apigenerationloans.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Client with ID " + id + " not found.");
    }
}

