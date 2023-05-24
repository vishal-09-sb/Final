package com.movie.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.book.model.Movie;
import com.movie.book.model.Ticket;
import com.movie.book.service.MovieService;
import com.movie.book.service.TicketService;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ts;
	
	@Autowired
	private MovieService ms;
	
	@PostMapping("/add/{mid}")
	public ResponseEntity<?> addTicket(@PathVariable("mid")int movieId, @RequestBody Ticket ticket)
	{
		Movie m1 = ms.getMovieById(movieId);
		
		if(m1 !=null)
		{
			m1.setMovieName(ticket.getMovieName());
			
			ticket.setMovie_id_fk(ticket.getMovie_id_fk());
			ticket.setTotalSeat(ticket.getTotalSeat());
			ticket.setSeatsAvailable(ticket.getSeatsAvailable());
			ticket.setSeatsBooked(ticket.getSeatsBooked());
			
			if(ms.updateMovie(m1) && ts.addTicket(ticket))
			{
				return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<String>("Ticket cannot be created", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
