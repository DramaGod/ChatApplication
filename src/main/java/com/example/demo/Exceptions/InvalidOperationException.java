package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request Not Found")
public class InvalidOperationException extends RuntimeException{

    public InvalidOperationException(){
    }

    public InvalidOperationException(String message){
        super(message);
    }
}

