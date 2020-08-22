package com.appinventiv.assignment.movie_rest;

import com.appinventiv.assignment.movie_rest.domain.Category;
import com.appinventiv.assignment.movie_rest.domain.Movie;
import com.appinventiv.assignment.movie_rest.repository.CategoryRepository;
import com.appinventiv.assignment.movie_rest.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        createMovies();
        createCategories();

    }

    private void createMovies() {
        Movie movie = new Movie("The Avengers");
        Category actionCat = new Category("Action");
        movie.setCategory(actionCat);
        movie.setRating(4.6F);
        movieRepository.save(movie);
    }

    private void createCategories() {

        Category horrorCat = new Category("Horror");
        Category dramaCat = new Category("Drama");
        Category thrillerCat = new Category("Thriller");
        categoryRepository.saveAll(Arrays.asList(horrorCat,dramaCat,thrillerCat));
    }
}
