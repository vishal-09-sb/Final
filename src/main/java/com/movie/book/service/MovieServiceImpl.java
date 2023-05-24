package com.movie.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.movie.book.exceptions.DuplicateMovieIdExceptions;
import com.movie.book.exceptions.DuplicateMovieNameException;
import com.movie.book.model.Movie;
import com.movie.book.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepo;

	@Override
	public List<Movie> getAllMovies() {
		List<Movie> movielist = movieRepo.findAll();
		if(movielist != null && movielist.size() > 0)
		{
			return movielist;
		}
		return null;
	}

	@Override
	public Movie addMovie(Movie movie) throws DuplicateMovieNameException {
		
		Optional<Movie> movieObj = movieRepo.findByMovieName(movie.getMovieName());
		
		if(movieObj.isPresent())
		{
			throw new DuplicateMovieNameException();
		}
		return movieRepo.saveAndFlush(movie);
	}

	@Override
	public boolean deleteMovie(int mid) {
		Optional<Movie> movieObj = movieRepo.findById(mid);
		
		if(movieObj.isPresent())
		{
		movieRepo.deleteById(mid);
		return true;
		}
		return false;
	}

	@Override
	public boolean updateMovie(Movie movie) {
		
		 Optional<Movie> movie1 = movieRepo.findById(movie.getMovieId());
		 
		if(movie1.isPresent())
		{
			Movie movieObj = movie1.get();
			movieObj.setMovieName(movie.getMovieName());
			movieRepo.save(movieObj);
			return true;
		}

		return false;
	}

	@Override
	public Movie getMovieById(int mid) {
		Optional<Movie> movie = movieRepo.findById(mid);
		if(movie.isPresent())
		{
			return movie.get();
		}
		
		return null;
	}

	@Override
	public Movie getMovieByName(String name) {
		Optional<Movie> movieObj =  movieRepo.findByMovieName(name);
		
		if(movieObj.isPresent()) {
			
			return movieObj.get();
		}
		return null; 
	}

	
	

}
