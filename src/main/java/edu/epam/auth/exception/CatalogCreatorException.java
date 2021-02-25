package edu.epam.auth.exception;

public class CatalogCreatorException extends Exception{

    public CatalogCreatorException() {
        super();
    }

    public CatalogCreatorException(String s) {
        super(s);
    }

    public CatalogCreatorException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CatalogCreatorException(Throwable throwable) {
        super(throwable);
    }
}
