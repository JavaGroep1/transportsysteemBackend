package com.joep.backofficeapi.DAL.Interfaces;

import com.joep.backofficeapi.Models.Ticket.Ticket;
import com.joep.backofficeapi.Models.Ticket.TicketReply;
import com.joep.backofficeapi.Models.Ticket.TicketStatus;
import org.bson.types.ObjectId;

import java.util.List;

public interface ITicketStore {
    public void addReply(Ticket ticket, TicketReply ticketReply);
    void addTicket(Ticket ticket);
    Ticket getTicketById(ObjectId id);
    List<Ticket> getTickets();
    List<Ticket> getPendingTickets();
    List<Ticket> getCompletedTickets();
    List<Ticket> getInProgressTickets();
    void changeTicketStatus(Ticket ticket, TicketStatus status);

}
