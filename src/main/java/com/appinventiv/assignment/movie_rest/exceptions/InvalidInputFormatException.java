package com.appinventiv.assignment.movie_rest.exceptions;

import static com.appinventiv.assignment.movie_rest.constants.message.InternalExceptionMessages.INVALID_PARAM_FORMAT;

public class InvalidInputFormatException extends RuntimeException  {

    InvalidInputFormatException(){
        super(INVALID_PARAM_FORMAT);
    }

}
