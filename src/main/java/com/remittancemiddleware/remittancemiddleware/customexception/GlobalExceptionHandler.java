package com.remittancemiddleware.remittancemiddleware.customexception;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface GlobalExceptionHandler {
    @ExceptionHandler
        //BAD REQUEST 400
    ResponseEntity<CustomResponse> handleException(CustomBadRequestException exc);

    @ExceptionHandler
        //NOT FOUND 404
    ResponseEntity<CustomResponse> handleException(CustomNotFoundException exc);

    @ExceptionHandler
        //INTERNAL SERVER ERROR 500
    ResponseEntity<CustomResponse> handleException(Exception exc);
}
