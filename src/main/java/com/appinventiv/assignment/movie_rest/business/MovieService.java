package com.appinventiv.assignment.movie_rest.business;

import com.appinventiv.assignment.movie_rest.domain.Movie;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

@Service
public interface MovieService {

    Movie findMovieById(Integer id);
    List<Movie> findAllMovies();

    Movie addMovie(Movie movie);
    Object updateMovie(Movie movie);
    boolean deleteMovieById(Integer id);



}
