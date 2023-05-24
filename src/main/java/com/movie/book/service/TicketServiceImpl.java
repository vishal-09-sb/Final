package com.movie.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.book.model.Ticket;
import com.movie.book.repository.TicketRepo;

@Service
public class TicketServiceImpl implements TicketService{
	
	@Autowired
	private TicketRepo ticketRepo;

	@Override
	public List<Ticket> getAllTickets(int mid) {
		List<Ticket> ticketlist = ticketRepo.getTicketList(mid);
		return ticketlist;
	}

	@Override
	public boolean addTicket(Ticket ticket) {
		Ticket ticketObj = new Ticket();
		
		ticketObj.setMovieName(ticket.getMovieName());
		ticketObj.setMovie_id_fk(ticket.getMovie_id_fk());
		ticketObj.setTotalSeat(ticket.getTotalSeat());
		ticketObj.setSeatsAvailable(ticket.getSeatsAvailable());
		ticketObj.setSeatsBooked(ticket.getSeatsBooked());
		
		ticketRepo.saveAndFlush(ticketObj);
		
		return true;
	}

	@Override
	public boolean deleteTicket(int mid) {
		ticketRepo.deleteTicketData(mid);
		return true;
	}

}
