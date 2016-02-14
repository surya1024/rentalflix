package io.egen.rentalflix;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for MovieService
 */
public class MovieServiceTest {
	MovieService movieService = new MovieService();

	@Before
	public void init() {
		movieService.moveStore.add(new Movie(1, "test", "english", "2001"));
		movieService.moveStore.add(new Movie(2, "test", "english", "2005"));
		movieService.moveStore.add(new Movie(3, "test1", "english", "2005"));
		movieService.movieID = 3;
	}

	@Test
	public void findALLTest() {
		List<Movie> movieList = movieService.findAll();
		assertEquals(3, movieList.size());
	}

	@Test
	public void findByNametest() {
		List<Movie> movieList = movieService.findByName("test");
		assertEquals(2, movieList.size());
	}

	@Test
	public void creatTest() {
		Movie testMovie = movieService.create(new Movie("test1", "english", "2000"));
		assertEquals(4, testMovie.id);
	}

	@Test
	public void updateTest() {
		Movie testMovie = movieService.update(new Movie(1, "test", "english", "2010"));
		assertEquals("2010", testMovie.year);
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateTestIdNotFound() {
		movieService.update(new Movie(4, "test", "english", "2010"));
	}

	@Test
	public void deleteTest() {
		Movie testMovie = movieService.delete(1);
		assertEquals(1, testMovie.id);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteTestIdNotFound() {
		movieService.delete(4);
	}

	@Test
	public void rentMovieTest() {
		boolean expected = movieService.rentMovie(1, "Surya");
		assertEquals(true, expected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void rentMovieIdNotFound() {
		movieService.rentMovie(4, "Surya");
	}

	@Test(expected = IllegalArgumentException.class)
	public void rentAlreadyRented() {
		movieService.rentMovie(1, "Surya");
		movieService.rentMovie(1, "Surya");
	}
}
