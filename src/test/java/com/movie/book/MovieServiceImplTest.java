package com.movie.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.movie.book.model.Movie;
import com.movie.book.repository.MovieRepository;
import com.movie.book.service.MovieServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest

public class MovieServiceImplTest {
	
	@Mock
	private MovieServiceImpl movieService;
	
	@InjectMocks
	private MovieRepository movieRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(movieService).build();
	}
	
	List<Movie> movieList = new ArrayList<Movie>();
	
	@Test
	public void getAllMoviesSuccess() throws Exception
	{
		Movie movie = new Movie();
		movie.setMovieId(1);
		movie.setMovieName("jii");
		movie.setTheatreName("PVR");
		movie.setSeatsAvailable(0);
		
		movieList.add(movie);
		when(movieRepository.findAll()).thenReturn(movieList);
		
		List<Movie> mList = movieService.getAllMovies();
		assertEquals(movieList, mList);
		
	}
	
	@Test
	public void getAllMoviesFailure() throws Exception
	{
		
		when(movieRepository.findAll()).thenReturn(null);
		
		List<Movie> mList = movieService.getAllMovies();
		assertNull(mList);
		
	}
	
	@Test
	public void addMovieSuccess() throws Exception
	{
		Movie movie = new Movie();
		movie.setMovieId(1);
		movie.setMovieName("jii");
		movie.setTheatreName("PVR");
		movie.setSeatsAvailable(0);
		
		movieList.add(movie);
		when(movieRepository.save(any())).thenReturn(movie);
		
		Movie m1 = movieService.addMovie(movie);
		assertEquals(movie, m1);
		
	}

	@Test
	public void addMovieFailure() throws Exception
	{
		
		when(movieRepository.save(any())).thenReturn(null);
		
		Movie m1 = movieService.addMovie(null);
		assertNull(m1);
		
	}
}



