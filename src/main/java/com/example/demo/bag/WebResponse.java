package com.example.demo.bag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class WebResponse extends Bag<WebResponse> {


    public static WebResponse create(){
        return new WebResponse();
    }

    public ResponseEntity giveSuccess(){
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity throwError(String message){
        return throwError(HttpStatus.BAD_REQUEST,message);
    }

    public ResponseEntity throwError(HttpStatus status,String message){
        return new ResponseEntity(message,status);
    }

    public ResponseEntity compactWeb(){
        return ResponseEntity.ok(this.compact());
    }

}
