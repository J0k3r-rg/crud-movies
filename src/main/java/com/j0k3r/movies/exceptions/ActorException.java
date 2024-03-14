package com.j0k3r.movies.exceptions;

public class ActorException extends Exception{

    public ActorException(String message){
        super(message);
    }

    public ActorException(String message, Throwable cause){
        super(message, cause);
    }

    public ActorException(Throwable cause){
        super(cause);
    }
}
