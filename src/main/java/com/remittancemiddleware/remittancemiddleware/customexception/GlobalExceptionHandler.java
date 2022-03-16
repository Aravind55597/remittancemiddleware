package com.remittancemiddleware.remittancemiddleware.customexception;

import com.remittancemiddleware.remittancemiddleware.dataclass.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//reference: https://www.baeldung.com/exception-handling-for-rest-with-spring
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    //BAD REQUEST 400
    public ResponseEntity<CustomResponse> handleException(CustomBadRequestException exc){

        //create CustomerErrorResponse
        CustomResponse error = new CustomResponse(
                exc.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    //NOT FOUND 404
    public ResponseEntity<CustomResponse> handleException(CustomNotFoundException exc){

        //create CustomerErrorResponse
        CustomResponse error = new CustomResponse(
                exc.getMessage(),
                HttpStatus.NOT_FOUND
        );

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

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
