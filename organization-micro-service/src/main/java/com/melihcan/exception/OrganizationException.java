package com.melihcan.exception;

import lombok.Getter;

@Getter
public class OrganizationException extends RuntimeException{

    private final EErrorType errorType;


    /**
     * Runtime dan miras aldığımız için hata mesajının kendısıne ıletılmesı gereklıdır.
     * @param errorType
     */
    public OrganizationException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public OrganizationException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}
