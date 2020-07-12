package com.tkouleris.funquizzler.exceptions;

public class UserExistsException extends Exception {
    public String message;

    public UserExistsException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}