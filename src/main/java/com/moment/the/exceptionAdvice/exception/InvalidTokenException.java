package com.moment.the.exception.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String msg, Throwable t){
        super(msg, t);
    }
    public InvalidTokenException(String msg){
        super(msg);
    }
    public InvalidTokenException(){
        super();
    }
}
