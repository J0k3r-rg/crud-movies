package com.j0k3r.movies.utils;

import com.j0k3r.movies.exceptions.PropertiesException;

public class Utils {

    public static void validateIdLong(Long id) throws PropertiesException {
        if (id == null || id <= 0) {
            throw new PropertiesException("Id is required or invalid");
        }
    }

}
