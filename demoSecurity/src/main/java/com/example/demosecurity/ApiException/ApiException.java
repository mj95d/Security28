package com.example.demosecurity.ApiException;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}