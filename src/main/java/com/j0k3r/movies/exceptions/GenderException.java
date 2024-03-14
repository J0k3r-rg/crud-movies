package com.j0k3r.movies.exceptions;

public class GenderException extends Exception{

    public GenderException(String message) {
        super(message);
    }

    public GenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenderException(Throwable cause) {
        super(cause);
    }

}
