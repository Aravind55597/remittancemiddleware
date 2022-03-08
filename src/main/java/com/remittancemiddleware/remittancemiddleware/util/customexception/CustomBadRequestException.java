package com.remittancemiddleware.remittancemiddleware.util.customexception;

public class CustomBadRequestException extends RuntimeException {

    public CustomBadRequestException() {
    }

    public CustomBadRequestException(String message) {
        super(message);
    }

    public CustomBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomBadRequestException(Throwable cause) {
        super(cause);
    }

//    public CustomBadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
//    }
}
