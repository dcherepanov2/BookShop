package org.example.exception;

public class BookShelfLoginException extends Exception  {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BookShelfLoginException(String message) {
        this.message = message;
    }
}
