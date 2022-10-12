package com.bt.marketplace.partnercredentials.exceptions;


public class ProcessingException extends RuntimeException {
    public ProcessingException ( Exception e ) {
        super(e);
    }
}
