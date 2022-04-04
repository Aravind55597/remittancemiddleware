package com.remittancemiddleware.remittancemiddleware.customexception;

import java.util.HashMap;

public class CustomMappingException extends CustomException {
    public CustomMappingException(String message) {
        super(message);
    }

    public CustomMappingException(HashMap<String, String> errors) {
        super(errors);
    }

    public CustomMappingException(String message, HashMap<String, String> errors) {
        super(message, errors);
    }

    public CustomMappingException(String message, Throwable cause, HashMap<String, String> errors) {
        super(message, cause, errors);
    }

    public CustomMappingException(Throwable cause, HashMap<String, String> errors) {
        super(cause, errors);
    }
}
