package com.appinventiv.assignment.movie_rest.repository;

import com.appinventiv.assignment.movie_rest.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie,Integer> {

    @Override
    List<Movie> findAll();


    @Override
    Optional<Movie> findById(Integer aLong);

    @Override
    Movie save(Movie movie);

    @Override
    void deleteById(Integer id);

    @Override
    void delete(Movie movie);
}
