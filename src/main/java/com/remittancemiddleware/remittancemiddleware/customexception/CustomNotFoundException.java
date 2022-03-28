package com.remittancemiddleware.remittancemiddleware.customexception;

import java.util.HashMap;

public class CustomNotFoundException  extends CustomException{

    public CustomNotFoundException(String message) {
        super(message);
    }

    public CustomNotFoundException(HashMap<String, String> errors) {
        super(errors);
    }

    public CustomNotFoundException(String message, HashMap<String, String> errors) {
        super(message, errors);
    }

    public CustomNotFoundException(String message, Throwable cause, HashMap<String, String> errors) {
        super(message, cause, errors);
    }

    public CustomNotFoundException(Throwable cause, HashMap<String, String> errors) {
        super(cause, errors);
    }
}
