package com.appinventiv.assignment.movie_rest;

import com.appinventiv.assignment.movie_rest.controller.MovieController;
import com.appinventiv.assignment.movie_rest.domain.Category;
import com.appinventiv.assignment.movie_rest.domain.Movie;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;



@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovieRestApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private MovieController movieController;

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	public void contexLoads() throws Exception {
		assertThat(movieController).isNotNull();
	}


	@Test
	@Order(1)
	public void addMovie() throws Exception {
		Movie movie = new Movie();
		movie.setName("Extraction");
		movie.setCategory(new Category(5));
		movie.setRating(4.0F);

		ResponseEntity<Movie> createdMovie = restTemplate.postForEntity("http://localhost:"+port+"/iMovie/movies/add",
				movie,
				Movie.class);

		assertNotNull(createdMovie);
		System.out.println(createdMovie.getBody().getId());
		assertThat(createdMovie.getBody().getId()!=null && createdMovie.getBody().getId()>0);
	}


	@Test
	@Order(2)
	public void updateMovie() throws Exception {
		Movie movie = new Movie();
		movie.setId(1);
		movie.setName("The Avengers");
		movie.setCategory(new Category(2,"Action"));
		movie.setRating(4.3F);
		HttpEntity<Movie> movieHttpEntity = new HttpEntity<Movie>(movie);
		// execute
		ResponseEntity<Movie> responseEntity = restTemplate.exchange("http://localhost:"+port+"/iMovie/movies/update",HttpMethod.PUT,
				movieHttpEntity,
				Movie.class);

		// verify
		int status = responseEntity.getStatusCodeValue();
		Movie updatedMovie = responseEntity.getBody();
		assertNotNull(updatedMovie);
		assertThat(status == HttpStatus.OK.value());
		assertThat(updatedMovie.getRating().equals(movie.getRating()));

		System.out.println(" Movie Updated ");
	}


	@Test
	@Order(3)
	public void getAllMovies() throws Exception {
//		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/iMovie/movies/", List.class).size()>0);
		List<Movie> movies = restTemplate.getForObject("http://localhost:"+port+"/iMovie/movies/",List.class);

		// verify
		assertNotNull(movies);
		assertThat(movies.size()>0);
		System.out.println("Movie Count: "+movies.size());
	}



	@Test
	@Order(4)
	public void getMovies() throws Exception {
		Movie movie = restTemplate.getForObject("http://localhost:"+port+"/iMovie/movies/{id}",
				Movie.class,
				1);

		// verify
		assertNotNull(movie);
		assertThat(movie.getName()!=null);
	}

	@Test
	@Order(5)
	public void deleteMovie() throws Exception {
		ResponseEntity<Void> responseEntity = restTemplate.exchange("http://localhost:"+port+"/iMovie/movies/{id}",
				HttpMethod.DELETE,
				null,
				Void.class,
				1);

		// verify
		int status = responseEntity.getStatusCodeValue();
		System.out.println("Delete Status: "+status);
		assertThat(HttpStatus.OK.value()==status);
	}



}
