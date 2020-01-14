package org.apisign.exception;


/**
 * api异常
 */
public abstract class AbstractApiException extends RuntimeException{
    public AbstractApiException(String message) {
        super(message);
    }
}
