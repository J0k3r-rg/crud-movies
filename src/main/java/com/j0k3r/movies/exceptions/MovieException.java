package com.j0k3r.movies.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MovieException extends Exception{

    private Integer code;

    public MovieException(String message, Integer code){
        super(message);
    }
    public MovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieException(Throwable cause) {
        super(cause);
    }

}
