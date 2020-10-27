package com.joep.backofficeapi.Models.Ticket;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
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
    private String idString;

    private String title;
    private String body;

    private TicketStatus status;

    @Reference
    private ApplicationUser issuedBy;

    @JsonInclude
    private List<TicketReply> replies;

    private LocalDateTime dateIssued;

    public Ticket(String title, String body, ApplicationUser issuedBy) {
        this.title = title;
        this.body = body;
        this.status = TicketStatus.PENDING;
        this.issuedBy = issuedBy;
        this.dateIssued = LocalDateTime.now();
    }

    public Ticket() {
    }


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

    public void setIssuedBy(ApplicationUser issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getIdString() {
        return Id.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setReplies(List<TicketReply> replies) {
        this.replies = replies;
    }
}
