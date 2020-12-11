package com.joep.backofficeapi.Controllers;

import com.joep.backofficeapi.DAL.Containers.TicketContainer;
import com.joep.backofficeapi.DAL.Containers.UserStoreContainer;
import com.joep.backofficeapi.Exceptions.BadRequestException;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Requests.Ticket.AddTicketRequest;
import com.joep.backofficeapi.Models.Requests.Ticket.ChangeTicketStatusRequest;
import com.joep.backofficeapi.Models.Requests.Ticket.ReplyToTicketRequest;
import com.joep.backofficeapi.Models.Ticket.Ticket;
import com.joep.backofficeapi.Models.Ticket.TicketReply;
import com.joep.backofficeapi.Models.Ticket.TicketStatus;
import com.joep.backofficeapi.Util.Authorization.RoleAuthorization;
import com.joep.backofficeapi.Util.Email.EmailUtil;
import com.joep.backofficeapi.Util.JwtUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private RoleAuthorization roleAuthorization;

    @Autowired
    private TicketContainer ticketContainer;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    private UserStoreContainer userStoreContainer;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "", headers = "Accept=application/json")
    ResponseEntity<?> addTicket(HttpServletRequest req, @RequestBody AddTicketRequest ticket) throws Exception {
        var user = userStoreContainer.getUserByName(jwtUtil.extractUsername(req));
        Ticket ticketToAdd = new Ticket(ticket.getTitle(), ticket.getBody(), user);
        ticketContainer.addTicket(ticketToAdd);
        emailUtil.sendEmail(new String[] {user.getEmail()}, "New ticket created", "We received your ticket request. You can view it on the website at any time");
        return ResponseEntity.ok("Created");

    }

    @GetMapping(path = "/{id}")
    Ticket getTicket(@PathVariable("id") String Id){
        return ticketContainer.getTicketById(new ObjectId(Id));
    }


    @GetMapping(params = "status", produces = "application/json")
    List<Ticket> getTicketByStatus(TicketStatus status) throws BadRequestException {
        return ticketContainer.getTicketByStatus(status);
    }

    @GetMapping(params = "client", produces = "application/json")
    List<Ticket> getTicketByClient(String client) throws Exception {
        var customer= userStoreContainer.getUserByName(jwtUtil.extractUsername(client));
        return ticketContainer.getTicketsByCustomer(customer);
    }

    @GetMapping( params = {"client", "status"}, produces = "application/json")
    List<Ticket> getTicketByClientAndStatus(String client, TicketStatus status) throws Exception {
        var customer= userStoreContainer.getUserById(new ObjectId(client));
        return ticketContainer.getTicketByStatusAndClient(status, customer);
    }

    @PostMapping("/reply")
    ResponseEntity<?> replyToTicket(HttpServletRequest req, @RequestBody ReplyToTicketRequest reply) throws Exception {
        var user = userStoreContainer.getUserByName(jwtUtil.extractUsername(req));
        var ticket = ticketContainer.getTicketById(reply.getTicketId());
        var replyToAdd = new TicketReply(reply.getReplyBody(), userStoreContainer.getUserByName(jwtUtil.extractUsername(req)));
        ticketContainer.addReply(ticket, replyToAdd);
        if (ticket.getStatus() == TicketStatus.PENDING){
            ticketContainer.changeTicketStatus(ticket, TicketStatus.IN_PROGRESS);
        }
        if (user.getRole().equals(Roles.Employee) || user.getRole().equals(Roles.Admin)) {
            emailUtil.sendEmail(new String[]{ticket.getIssuedBy().getEmail()}, "New reply to your Ticket (" + ticket.getIdString() + ")", "One of our support staff has replied to your ticket, visit the website to view the reply");
        }
        return ResponseEntity.ok("Replied");
    }
    @PutMapping("/changestatus")
    ResponseEntity<?> changeTicketStatus(HttpServletRequest req, @RequestBody ChangeTicketStatusRequest ticketStatusRequest){
        var ticket = ticketContainer.getTicketById(new ObjectId(ticketStatusRequest.ticketId));
        ticketContainer.changeTicketStatus(ticket, ticketStatusRequest.getNewStatus());
        emailUtil.sendEmail(new String[]{ticket.getIssuedBy().getEmail()}, "Status of ticket "+ticket.getIdString()+" has been changed", "The status of your ticket has been changed to " + ticketStatusRequest.getNewStatus());

        return ResponseEntity.ok("Updated ticket status");
    }

}
