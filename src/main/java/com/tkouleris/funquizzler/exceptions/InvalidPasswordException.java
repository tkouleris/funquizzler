package com.tkouleris.funquizzler.exceptions;

public class InvalidPasswordException extends Exception {
    public String message;

    public InvalidPasswordException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}