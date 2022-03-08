package com.remittancemiddleware.remittancemiddleware.util.responsemodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {
    private T data ;
    private Boolean succeeded ;
    private String message ;
    private int httpCode;


    //Success response with message
    public Response(T data, String msg)
    {
        this.succeeded = true;
        this.message = msg;
        this.data = data;
        this.httpCode = HttpStatus.OK.value();
    }

    //Success response without message
    public Response(T data)
    {
        this.succeeded = true;
        this.message = "";
        this.data = data;
        this.httpCode = HttpStatus.OK.value();
    }



    //Error response
    public Response(String errorMsg , HttpStatus httpStatus)
    {
        this.succeeded = false;
        this.message = errorMsg;
        this.data = null;
        this.httpCode = httpStatus.value();
    }


}
