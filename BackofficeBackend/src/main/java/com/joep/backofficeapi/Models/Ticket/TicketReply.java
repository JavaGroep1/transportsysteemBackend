package com.joep.backofficeapi.Models.Ticket;

import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Entity

public class TicketReply {

    @dev.morphia.annotations.Id
    public ObjectId Id;

    public String body;

    @Reference
    public ApplicationUser replyBy;

    public LocalDateTime dateReplied;

    public TicketReply() {
    }

    public TicketReply(String body, ApplicationUser replyBy) {
        this.body = body;
        this.replyBy = replyBy;
        this.dateReplied = LocalDateTime.now();
    }
}
