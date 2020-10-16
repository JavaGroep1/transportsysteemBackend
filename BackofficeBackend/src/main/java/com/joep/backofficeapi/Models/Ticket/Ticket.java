package com.joep.backofficeapi.Models.Ticket;

import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Ticket {

    @Id
    private ObjectId Id;
    private String title;
    private String body;

    private TicketStatus status;
    @Reference
    private ApplicationUser issuedBy;

    private List<TicketReply> replies;

    private LocalDateTime dateIssued;

    public ObjectId getId() {
        return Id;
    }

    public List<TicketReply> getReplies() {
        return replies;
    }

    public LocalDateTime getDateIssued() {
        return dateIssued;
    }

    public ApplicationUser getIssuedBy() {
        return issuedBy;
    }
}
