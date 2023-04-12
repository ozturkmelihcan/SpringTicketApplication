package com.melihcan.exception;

import lombok.Getter;

@Getter
public class TicketException extends RuntimeException{

    private final EErrorType errorType;


    /**
     * Runtime dan miras aldığımız için hata mesajının kendısıne ıletılmesı gereklıdır.
     * @param errorType
     */
    public TicketException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public TicketException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
