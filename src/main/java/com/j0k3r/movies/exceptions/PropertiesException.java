package com.j0k3r.movies.exceptions;

public class PropertiesException extends Exception{

        public PropertiesException(String message) {
            super(message);
        }

        public PropertiesException(String message, Throwable cause) {
            super(message, cause);
        }

        public PropertiesException(Throwable cause) {
            super(cause);
        }
}