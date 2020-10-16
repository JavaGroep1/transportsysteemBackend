package com.joep.backofficeapi.Models.Ticket;

import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class TicketReply {

    @dev.morphia.annotations.Id
    private ObjectId Id;

    private String body;

    @Reference
    private ApplicationUser replyBy;

    private LocalDateTime dateReplied;
}
