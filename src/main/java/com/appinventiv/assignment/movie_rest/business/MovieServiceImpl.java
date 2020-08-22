package com.appinventiv.assignment.movie_rest.business;

import com.appinventiv.assignment.movie_rest.domain.Category;
import com.appinventiv.assignment.movie_rest.domain.Movie;
import com.appinventiv.assignment.movie_rest.exceptions.GenericOpFailedException;
import com.appinventiv.assignment.movie_rest.exceptions.MovieNotFoundException;
import com.appinventiv.assignment.movie_rest.repository.CategoryRepository;
import com.appinventiv.assignment.movie_rest.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.appinventiv.assignment.movie_rest.constants.message.APIExceptionMessages.SOMETHING_WENT_WRONG;
import static com.appinventiv.assignment.movie_rest.constants.message.InternalExceptionMessages.NEW_CATEGORY_CREATED;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    final MovieRepository movieRepository;
    final CategoryRepository categoryRepository;

    public MovieServiceImpl(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Movie findMovieById(Integer id) {
        Optional<Movie> movieWrapper = movieRepository.findById(id);
        if (movieWrapper.isPresent()){
            return movieWrapper.get();
        }else {
            throw new MovieNotFoundException(id);
        }
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie addMovie(Movie movie) {
        // Check if category already exists
        if (movie.getCategory()!=null){
            Optional<Category> oldCategory = categoryRepository.findByNameIgnoreCase(movie.getCategory().getName());
            if (oldCategory.isPresent()){
                movie.setCategory(oldCategory.get());
            }else {
                movie.setCategory(categoryRepository.save(movie.getCategory()));
                LOGGER.info(NEW_CATEGORY_CREATED);
            }
        }

        Optional<Movie> movieWrapper = Optional.ofNullable(movieRepository.save(movie));
        if (movieWrapper.isPresent()){
            return movieWrapper.get();
        }else {
            throw new MovieNotFoundException(movie.getName());
        }

    }

    @Override
    public Object updateMovie(Movie movie){
        if (movie!=null && movie.getId()!=null && movie.getId()>0){
            return movieRepository.save(movie);
        }else {
            throw new GenericOpFailedException(SOMETHING_WENT_WRONG);
        }
    }


    @Override
    public boolean deleteMovieById(Integer id) {
        if (validateId(id)){
            movieRepository.delete(new Movie(id));
            return true;
        }
        return false;
    }

    private boolean validateId(Integer id) {
        return id!=null && id>0;
    }
}
