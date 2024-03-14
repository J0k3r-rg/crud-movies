package com.j0k3r.movies.utils;

import com.j0k3r.movies.exceptions.ActorException;
import com.j0k3r.movies.exceptions.GenderException;
import com.j0k3r.movies.exceptions.MovieException;
import com.j0k3r.movies.exceptions.PropertiesException;
import com.j0k3r.movies.http.response.ErrorResponse;

public class ErrorResponseUtils {

    public static ErrorResponse generateErrorResponse(Exception e){
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(obtainCodeException(e))
                .build();
    }

    private static Integer obtainCodeException(Exception e){
        if (e instanceof ActorException)
            return ((ActorException) e).getCode();

        if (e instanceof GenderException)
            return ((GenderException) e).getCode();

        if (e instanceof MovieException)
            return ((MovieException) e).getCode();

        if (e instanceof RuntimeException)
            return 489;
        if (e instanceof PropertiesException)
            return ((PropertiesException) e).getCode();
        return 400;
    }

}
