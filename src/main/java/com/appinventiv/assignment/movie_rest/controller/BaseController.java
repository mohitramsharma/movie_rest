package com.appinventiv.assignment.movie_rest.controller;

import com.appinventiv.assignment.movie_rest.exceptions.InvalidInputFormatException;
import com.appinventiv.assignment.movie_rest.exceptions.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.appinventiv.assignment.movie_rest.constants.message.APIExceptionMessages.*;

@ControllerAdvice
@ApiIgnore
public class BaseController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Object> movieNotFoundExceptionResponse(
            MovieNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, MOVIE_NOT_FOUND_BY_PARAM);

        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidInputFormatException.class)
    public ResponseEntity<Object> invalidInputFormatExceptionResponse(
            InvalidInputFormatException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, INVALID_PARAM_FORMAT);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
