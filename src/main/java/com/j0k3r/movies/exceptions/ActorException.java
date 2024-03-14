package com.j0k3r.movies.exceptions;

import lombok.Getter;

@Getter
public class ActorException extends Exception{

    public Integer code;

    public ActorException(String message, Integer code){
        super(message);
        this.code = code;
    }

    public ActorException(String message, Throwable cause, Integer code){
        super(message, cause);
        this.code = code;
    }

    public ActorException(Throwable cause){
        super(cause);
    }
}
