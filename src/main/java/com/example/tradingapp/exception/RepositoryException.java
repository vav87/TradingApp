package com.example.tradingapp.exception;

public class RepositoryException extends RuntimeException{

    public static final String SIGNAL_NOT_EXISTS = "Signal %s does not exist";

    public RepositoryException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
