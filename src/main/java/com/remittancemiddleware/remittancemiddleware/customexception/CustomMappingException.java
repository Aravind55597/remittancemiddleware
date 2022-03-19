package com.remittancemiddleware.remittancemiddleware.customexception;

public class CustomMappingException extends RuntimeException{


    public CustomMappingException() {
    }

    public CustomMappingException(String message) {
        super(message);
    }

    public CustomMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomMappingException(Throwable cause) {
        super(cause);
    }
}
