package com.remittancemiddleware.remittancemiddleware.dataclass.custom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class CustomResponse<T> {
    private T data ;
    private Boolean succeeded ;
    private String message ;
    private int httpCode;


    //Success response with message
    public CustomResponse(T data, String msg)
    {
        this.succeeded = true;
        this.message = msg;
        this.data = data;
        this.httpCode = HttpStatus.OK.value();
    }

    //Success response without message
    public CustomResponse(T data)
    {
        this.succeeded = true;
        this.message = "";
        this.data = data;
        this.httpCode = HttpStatus.OK.value();
    }



    //Error response
    public CustomResponse(String errorMsg , HttpStatus httpStatus)
    {
        this.succeeded = false;
        this.message = errorMsg;
        this.data = null;
        this.httpCode = httpStatus.value();
    }


    public CustomResponse(String errorMsg , HttpStatus httpStatus , T errors)
    {
        this.succeeded = false;
        this.message = errorMsg;
        this.data = errors;
        this.httpCode = httpStatus.value();
    }



}
