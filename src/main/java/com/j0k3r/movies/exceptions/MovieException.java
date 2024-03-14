package com.j0k3r.movies.exceptions;

import lombok.Getter;

@Getter
public class MovieException extends Exception{

    private Integer code;

    public MovieException(String message, Integer code){
        super(message);
        this.code = code;
    }
    public MovieException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public MovieException(Throwable cause) {
        super(cause);
    }

}
