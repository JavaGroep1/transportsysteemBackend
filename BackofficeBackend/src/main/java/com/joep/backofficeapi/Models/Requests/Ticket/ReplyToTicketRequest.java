package com.joep.backofficeapi.Models.Requests.Ticket;

import org.bson.types.ObjectId;

public class ReplyToTicketRequest {
    private String ticketId;
    private String replyBody;


    public ObjectId getTicketId() {
        return new ObjectId(ticketId);
    }

    public String getReplyBody() {
        return replyBody;
    }
}
