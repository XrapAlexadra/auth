package edu.epam.auth.exception;

public class EncodeServiceException extends Exception{

    public EncodeServiceException() {
        super();
    }

    public EncodeServiceException(String s) {
        super(s);
    }

    public EncodeServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EncodeServiceException(Throwable throwable) {
        super(throwable);
    }
}
