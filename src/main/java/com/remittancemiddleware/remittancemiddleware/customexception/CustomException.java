package com.remittancemiddleware.remittancemiddleware.customexception;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
public abstract class CustomException extends RuntimeException {

    private HashMap<String, String> errors;
    private ArrayList<String> errorsA;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public CustomException(String message, HashMap<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public CustomException(String message, ArrayList<String> errorsA) {
        super(message);
        this.errorsA = errorsA;
    }

    public CustomException(String message, Throwable cause, HashMap<String, String> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public CustomException(Throwable cause, HashMap<String, String> errors) {
        super(cause);
        this.errors = errors;
    }
}
