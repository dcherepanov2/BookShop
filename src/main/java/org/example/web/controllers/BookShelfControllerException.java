package org.example.web.controllers;

public class BookShelfControllerException extends Exception  {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BookShelfControllerException(String message) {
        this.message = message;
    }
}
