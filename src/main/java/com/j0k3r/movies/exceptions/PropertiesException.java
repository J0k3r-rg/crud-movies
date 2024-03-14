package com.j0k3r.movies.exceptions;

import lombok.Getter;

@Getter
public class PropertiesException extends Exception{

    private Integer code;

    public PropertiesException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public PropertiesException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public PropertiesException(Throwable cause) {
            super(cause);
        }
}
