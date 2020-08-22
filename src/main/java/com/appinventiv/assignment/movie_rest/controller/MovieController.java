package com.appinventiv.assignment.movie_rest.controller;

import com.appinventiv.assignment.movie_rest.business.MovieService;
import com.appinventiv.assignment.movie_rest.constants.message.ApiInfo;
import com.appinventiv.assignment.movie_rest.constants.message.RequestMappings;
import com.appinventiv.assignment.movie_rest.domain.Movie;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RequestMappings.PATH_MOVIE)
public class MovieController {

    final
    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping(RequestMappings.PATH_ID)
    @ApiOperation(ApiInfo.INFO_FIND)
    ResponseEntity<Object> getMovieById(@PathVariable Integer id){
        return new ResponseEntity<>(movieService.findMovieById(id), HttpStatus.OK);
    }

    @GetMapping(RequestMappings.PATH_ALL)
    @ApiOperation(ApiInfo.INFO_FIND_ALL)
    ResponseEntity<List<Object>> getAllMovie(){
        return new ResponseEntity(movieService.findAllMovies(), HttpStatus.OK);
    }

    @PostMapping(RequestMappings.PATH_ADD)
    @ApiOperation(ApiInfo.INFO_ADD)
    ResponseEntity<Object> addMovie(@Valid @RequestBody Movie movie){
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.CREATED);
    }

    @PutMapping(RequestMappings.PATH_UPDATE)
    @ApiOperation(ApiInfo.INFO_UPDATE)
    ResponseEntity<Object> updateMovie(@Valid @RequestBody Movie movie){
        return new ResponseEntity<>(movieService.updateMovie(movie), HttpStatus.OK);
    }

    @DeleteMapping(RequestMappings.PATH_ID)
    @ApiOperation(ApiInfo.INFO_DELETE)
    ResponseEntity<Object> deleteMovie(@PathVariable Integer id){
        return new ResponseEntity<>(movieService.deleteMovieById(id), HttpStatus.OK);
    }
}
