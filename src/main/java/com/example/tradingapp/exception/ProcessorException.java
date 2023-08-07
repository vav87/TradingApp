package com.example.tradingapp.exception;

public class ProcessorException extends RuntimeException{

    public static final String WRONG_PARAMETERS_NUMBER = "Wrong number of parameters in the setAlgoParam method";
    public static final String WRONG_PARAMETERS = "Wrong format of parameters: %s , %s. Parameters should be Integers";
    public static final String WRONG_METHOD_NAME = "Method %s dos not exist";

    public ProcessorException(String message) {
        super(message);
    }

    public ProcessorException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
