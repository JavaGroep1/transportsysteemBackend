package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.TicketContainer;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.Models.Requests.Ticket.AddTicketRequest;
import com.joep.backofficeapi.Models.Requests.Ticket.ReplyToTicketRequest;
import com.joep.backofficeapi.Models.Ticket.Ticket;
import com.joep.backofficeapi.Models.Ticket.TicketReply;
import com.joep.backofficeapi.Util.JwtUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = {"*"})
@RestController
public class TicketController {
    @Autowired
    private TicketContainer ticketContainer;

    @Autowired
    private UserStoreContainer userStoreContainer;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/tickets/add")
    ResponseEntity<?> addTicket(HttpServletRequest req, @RequestBody AddTicketRequest ticket) throws Exception {
        var user = userStoreContainer.getUserByName(jwtUtil.extractUsername(req));
        Ticket ticketToAdd = new Ticket(ticket.getTitle(), ticket.getBody(), user);
        System.out.println(ticketToAdd);
        ticketContainer.addTicket(ticketToAdd);
        return ResponseEntity.ok("Created");
    }

    @GetMapping("/tickets")
    Ticket getTicket(){

            var ticket = ticketContainer.getTicketById(new ObjectId("5f898cf41904c25503685a14"));
            return ticket;

    }

    @GetMapping("/tickets/completed")
    ResponseEntity<?> getCompletedTickets(){
        return ResponseEntity.ok(ticketContainer.getCompletedTickets());
    }
    @GetMapping("/tickets/pending")
    ResponseEntity<?> getPendingTickets(){
        return ResponseEntity.ok(ticketContainer.getPendingTickets());
    }
    @GetMapping("/tickets/inprogress")
    ResponseEntity<?> getinProgressTickets(){
        return ResponseEntity.ok(ticketContainer.getInProgressTickets());
    }
    @PostMapping("/tickets/reply")
    ResponseEntity<?> getTicketById(HttpServletRequest req, @RequestBody ReplyToTicketRequest reply) throws Exception {
        var ticket = ticketContainer.getTicketById(reply.getTicketId());
        var replyToAdd = new TicketReply(reply.getReplyBody(), userStoreContainer.getUserByName(jwtUtil.extractUsername(req)));
        ticketContainer.addReply(ticket, replyToAdd);
        return ResponseEntity.ok("Replied");
    }

}