package com.tkouleris.funquizzler.exceptions;

public class InvalidEmailException extends Exception {
    public String message;

    public InvalidEmailException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}