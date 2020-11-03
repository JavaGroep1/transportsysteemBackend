package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.ITicketStore;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Ticket.Ticket;
import com.joep.backofficeapi.Models.Ticket.TicketReply;
import com.joep.backofficeapi.Models.Ticket.TicketStatus;
import org.bson.types.ObjectId;

import java.util.List;

public class TicketContainer implements ITicketStore {

    private final ITicketStore ticketStore;

    public TicketContainer(ITicketStore ticketStore) {
        this.ticketStore = ticketStore;
    }
    @Override
    public void addReply(Ticket ticket, TicketReply ticketReply) {
        ticketStore.addReply(ticket, ticketReply);
    }

    @Override
    public void addTicket(Ticket ticket) {
        ticketStore.addTicket(ticket);
    }

    @Override
    public Ticket getTicketById(ObjectId id) {
       return ticketStore.getTicketById(id);
    }

    @Override
    public List<Ticket> getTicketsByCustomer(ApplicationUser customer) {
        return ticketStore.getTicketsByCustomer(customer);
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketStore.getTickets();
    }

    @Override
    public List<Ticket> getTicketByStatus(TicketStatus status) {
        return ticketStore.getTicketByStatus(status);
    }

    @Override
    public List<Ticket> getTicketByStatusAndClient(TicketStatus status, ApplicationUser customer) {
        return ticketStore.getTicketByStatusAndClient(status,customer);
    }


    @Override
    public void changeTicketStatus(Ticket ticket, TicketStatus status) {
        ticketStore.changeTicketStatus(ticket, status);
    }
}
