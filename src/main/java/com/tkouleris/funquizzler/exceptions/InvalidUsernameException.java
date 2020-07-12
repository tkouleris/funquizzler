package com.tkouleris.funquizzler.exceptions;

public class InvalidUsernameException extends Exception {
    public String message;

    public InvalidUsernameException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
