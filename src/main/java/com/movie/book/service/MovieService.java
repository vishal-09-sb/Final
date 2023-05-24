package com.movie.book.service;

import java.util.List;

import com.movie.book.exceptions.DuplicateMovieIdExceptions;
import com.movie.book.exceptions.DuplicateMovieNameException;
import com.movie.book.model.Movie;

public interface MovieService {

	public List<Movie> getAllMovies();

	public Movie addMovie(Movie movie) throws DuplicateMovieIdExceptions, DuplicateMovieNameException;

	public boolean deleteMovie(int mid);

	public boolean updateMovie(Movie movie);

	public Movie getMovieById(int mid);
	
	public Movie getMovieByName(String name);

}
