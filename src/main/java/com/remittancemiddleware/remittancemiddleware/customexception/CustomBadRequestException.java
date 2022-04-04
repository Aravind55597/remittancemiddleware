package com.remittancemiddleware.remittancemiddleware.customexception;


import java.util.ArrayList;
import java.util.HashMap;

public class CustomBadRequestException extends CustomException {

    public CustomBadRequestException(String message) {
        super(message);
    }

    public CustomBadRequestException(HashMap<String, String> errors) {
        super(errors);
    }

    public CustomBadRequestException(String message, HashMap<String, String> errors) {
        super(message, errors);
    }

    public CustomBadRequestException(String message, ArrayList<String> errors) {
        super(message, errors);
    }

    public CustomBadRequestException(String message, Throwable cause, HashMap<String, String> errors) {
        super(message, cause, errors);
    }

    public CustomBadRequestException(Throwable cause, HashMap<String, String> errors) {
        super(cause, errors);
    }
}
