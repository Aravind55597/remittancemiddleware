package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomBadRequestException;
import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;

//reference: https://www.baeldung.com/exception-handling-for-rest-with-spring
@ControllerAdvice
public class        GlobalExceptionHandlerImpl implements GlobalExceptionHandler {

    @Override
    @ExceptionHandler
    //BAD REQUEST 400
    public ResponseEntity<CustomResponse> handleException(CustomBadRequestException exc){
        //create CustomerErrorResponse
        if (exc.getErrors() == null) {
            CustomResponse<HashMap<String,String>> error = new CustomResponse(
                    exc.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    exc.getErrors()
            );
            //return ResponseEntity
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        else {
            CustomResponse<ArrayList<String>> error = new CustomResponse(
                    exc.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    exc.getErrorsA()
            );
            //return ResponseEntity
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    @ExceptionHandler
    //NOT FOUND 404
    public ResponseEntity<CustomResponse> handleException(CustomNotFoundException exc){

        //create CustomerErrorResponse
        CustomResponse<HashMap<String,String>> error = new CustomResponse(
                exc.getMessage(),
                HttpStatus.NOT_FOUND,
                exc.getErrors()
        );

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    @ExceptionHandler
    //INTERNAL SERVER ERROR 500
    public ResponseEntity<CustomResponse> handleException(Exception exc){

        //create CustomerErrorResponse
        CustomResponse error = new CustomResponse(
                exc.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
