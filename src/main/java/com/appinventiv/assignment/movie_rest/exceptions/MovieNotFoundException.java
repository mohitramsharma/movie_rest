package com.appinventiv.assignment.movie_rest.exceptions;

import static com.appinventiv.assignment.movie_rest.constants.message.InternalExceptionMessages.MOVIE_NOT_FOUND_BY_PARAM;

public class MovieNotFoundException extends RuntimeException  {

    public MovieNotFoundException(Object param){
        super(MOVIE_NOT_FOUND_BY_PARAM+ String.valueOf(param));
    }

}
