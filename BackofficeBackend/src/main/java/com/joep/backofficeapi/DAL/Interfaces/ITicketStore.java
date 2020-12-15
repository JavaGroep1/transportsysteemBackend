package com.joep.backofficeapi.DAL.Interfaces;

import com.joep.backofficeapi.Exceptions.TicketInvalidException;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Ticket.Ticket;
import com.joep.backofficeapi.Models.Ticket.TicketReply;
import com.joep.backofficeapi.Models.Ticket.TicketStatus;
import org.bson.types.ObjectId;

import java.util.List;

public interface ITicketStore {
    void addReply(Ticket ticket, TicketReply ticketReply);
    void addTicket(Ticket ticket) throws TicketInvalidException;
    Ticket getTicketById(ObjectId id);
    List<Ticket> getTicketsByCustomer(ApplicationUser customer);
    List<Ticket> getTickets();
    List<Ticket> getTicketByStatus(TicketStatus status);
    List<Ticket> getTicketByStatusAndClient(TicketStatus status, ApplicationUser customer);
    void changeTicketStatus(Ticket ticket, TicketStatus status);

}
