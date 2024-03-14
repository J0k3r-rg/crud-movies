package com.j0k3r.movies.exceptions;

import lombok.Getter;

@Getter
public class UserException extends Exception{

    private Integer code;

    public UserException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public UserException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public UserException(Throwable cause) {
        super(cause);
    }

}
