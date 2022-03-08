package com.remittancemiddleware.remittancemiddleware.util.customexception;

public class CustomNotFoundException extends RuntimeException{

    public CustomNotFoundException() {
    }

    public CustomNotFoundException(String message) {
        super(message);
    }

    public CustomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomNotFoundException(Throwable cause) {
        super(cause);
    }

//    public CustomNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
//    }
}
