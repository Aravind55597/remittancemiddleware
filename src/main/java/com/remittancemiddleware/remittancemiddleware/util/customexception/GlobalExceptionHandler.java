package com.remittancemiddleware.remittancemiddleware.util.customexception;

import com.remittancemiddleware.remittancemiddleware.util.customexception.CustomBadRequestException;
import com.remittancemiddleware.remittancemiddleware.util.customexception.CustomNotFoundException;
import com.remittancemiddleware.remittancemiddleware.util.responsemodel.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//reference: https://www.baeldung.com/exception-handling-for-rest-with-spring
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    //BAD REQUEST 400
    public ResponseEntity<Response> handleException(CustomBadRequestException exc){

        //create CustomerErrorResponse
        Response error = new Response(
                exc.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    //NOT FOUND 404
    public ResponseEntity<Response> handleException(CustomNotFoundException exc){

        //create CustomerErrorResponse
        Response error = new Response(
                exc.getMessage(),
                HttpStatus.NOT_FOUND
        );

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    //INTERNAL SERVER ERROR 500
    public ResponseEntity<Response> handleException(Exception exc){

        //create CustomerErrorResponse
        Response error = new Response(
                exc.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
