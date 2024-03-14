package com.j0k3r.movies.exceptions;

import lombok.Getter;

@Getter
public class RoleException extends Exception{

    private Integer code;

    public RoleException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public RoleException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public RoleException(Throwable cause) {
        super(cause);
    }
}
