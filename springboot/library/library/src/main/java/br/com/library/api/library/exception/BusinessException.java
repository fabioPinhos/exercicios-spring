package br.com.library.api.library.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
