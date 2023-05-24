package com.movie.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.movie.book.controller.MovieController;
import com.movie.book.model.Movie;
import com.movie.book.service.MovieServiceImpl;



@AutoConfigureMockMvc
@SpringBootTest

public class MovieControllerTest {
	
	@Mock
	private MovieServiceImpl movieService;
	
	@InjectMocks
	private MovieController movieController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
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
		when(movieService.getAllMovies()).thenReturn(movieList);
		
		List<Movie> mList = movieService.getAllMovies();
		assertEquals(movieList, mList);
		
mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllMovies").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	@Test
	public void getAllMoviesFailure() throws Exception
	{
		movieList.clear();
		when(movieService.getAllMovies()).thenReturn(movieList);
		
		assertEquals(0,movieList.size());
		
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/v1/getAllMovies").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}

}
