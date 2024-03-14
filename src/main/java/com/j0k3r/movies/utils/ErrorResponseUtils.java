package com.j0k3r.movies.utils;

import com.j0k3r.movies.http.response.ErrorResponse;

public class ErrorResponseUtils {

    public static ErrorResponse generateErrorResponse(Integer status, String message){
        return ErrorResponse.builder()
                .status(status)
                .message(message)
                .build();
    }

}
