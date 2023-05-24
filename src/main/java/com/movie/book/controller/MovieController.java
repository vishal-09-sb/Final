package com.movie.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.book.exceptions.DuplicateMovieIdExceptions;
import com.movie.book.exceptions.DuplicateMovieNameException;
import com.movie.book.model.Movie;
import com.movie.book.model.Ticket;
import com.movie.book.service.MovieService;
import com.movie.book.service.TicketService;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
	
	@Autowired
	private MovieService ms;
	
	@Autowired
	private TicketService ts;
	
	@PostMapping("/addMovie")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie) throws DuplicateMovieNameException, DuplicateMovieIdExceptions
	{
		if(ms.addMovie(movie)!=null)
		{
			return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("No Movie", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getAllMovies")
	public ResponseEntity<?> getMovies() 
	{
		List<Movie> movielist = ms.getAllMovies();
		if(movielist!=null)
		{
			for(Movie m: movielist)
			{
				List<Ticket> ticketlist = ts.getAllTickets(m.getMovieId());
				m.setTicketList(ticketlist);
			}

			return new ResponseEntity<List<Movie>>(movielist, HttpStatus.OK);
		}
		return new ResponseEntity<String>("No Movie list", HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping("/movieById/{mid}")
	public ResponseEntity<?> getMovieById(@PathVariable("mid") int movieId) 
	{
		Movie movieidexists = ms.getMovieById(movieId);
		if(movieidexists !=null)
		{
			List<Ticket> ticketlist = ts.getAllTickets(movieidexists.getMovieId());
			movieidexists.setTicketList(ticketlist);
			return new ResponseEntity<Movie>(movieidexists, HttpStatus.OK);
			
		}
		return new ResponseEntity<String>("No Movie with this ID", HttpStatus.NO_CONTENT);
		
	}
	@GetMapping("/movieByName/{name}")
	public ResponseEntity<?> getMovieByName(@PathVariable("name") String name) 
	{
		Movie movienameexists = ms.getMovieByName(name);
		if(movienameexists !=null)
		{
			List<Ticket> ticketlist = ts.getAllTickets(movienameexists.getMovieId());
			movienameexists.setTicketList(ticketlist);
			return new ResponseEntity<Movie>(movienameexists, HttpStatus.OK);
			
		}
		return new ResponseEntity<String>("No Movie with this Name", HttpStatus.NO_CONTENT);
		
	}
	
	@DeleteMapping("/delete/{mid}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("mid") int mid) 
	{
		if(ts.deleteTicket(mid) & ms.deleteMovie(mid))
		{
			return new ResponseEntity<String>("Movie deleted",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Movie not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/updateMovie")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie) 
	{
		if(ms.updateMovie(movie))
	  {
		  return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		  
	  }
	  return new ResponseEntity<String>("Movie could not be updated", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
