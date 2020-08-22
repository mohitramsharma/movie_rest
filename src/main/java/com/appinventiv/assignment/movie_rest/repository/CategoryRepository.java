package com.appinventiv.assignment.movie_rest.repository;

import com.appinventiv.assignment.movie_rest.domain.Category;
import com.appinventiv.assignment.movie_rest.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Integer> {

    @Override
    List<Category> findAll();


    @Override
    Optional<Category> findById(Integer id);

    @Override
    Category save(Category category);

    Category findByName(String name);

    @Override
    void deleteById(Integer id);

    @Override
    void delete(Category category);
}
