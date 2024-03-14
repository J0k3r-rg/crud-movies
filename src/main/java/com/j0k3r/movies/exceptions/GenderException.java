package com.j0k3r.movies.exceptions;

import lombok.Getter;

@Getter
public class GenderException extends Exception{

    private Integer code;

    public GenderException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public GenderException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public GenderException(Throwable cause) {
        super(cause);
    }

}
