package com.joep.backofficeapi.Models.Requests.Ticket;

import com.joep.backofficeapi.Models.Ticket.TicketStatus;

public class ChangeTicketStatusRequest {
    public String ticketId;

    public String getTicketId() {
        return ticketId;
    }

    public TicketStatus getNewStatus() {
        return newStatus;
    }

    public TicketStatus newStatus;

}
