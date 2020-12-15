package com.joep.backofficeapi.DAL.Containers;

import com.joep.backofficeapi.DAL.Interfaces.ITicketStore;
import com.joep.backofficeapi.Models.Authentication.ApplicationUser;
import com.joep.backofficeapi.Models.Authentication.Roles;
import com.joep.backofficeapi.Models.Ticket.Ticket;
import com.joep.backofficeapi.Models.Ticket.TicketReply;
import com.joep.backofficeapi.Models.Ticket.TicketStatus;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TicketContainerTest {

    private TicketContainer container;
    private ITicketStore ticketStore;

    @Before
    public void setup() {
        ticketStore = mock(ITicketStore.class);
        container = new TicketContainer(ticketStore);
    }
    @Test
    public void canAddTicket() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var sampleUser = new ApplicationUser("name", "pass", "email", Roles.Customer);
        var sampleTicket = new Ticket("title", "body", sampleUser);

        //execute
        container.addTicket(sampleTicket);
    }

    @Test
    public void canAddReply() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var sampleUser = new ApplicationUser("name", "pass", "email", Roles.Customer);
        var sampleTicket = new Ticket("title", "body", sampleUser);
        var ticketReply = new TicketReply("This is my reply!", sampleUser);
        //execute
        container.addReply(sampleTicket, ticketReply);
    }

    @Test
    public void getTicketByIdReturnsTicket() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var query = new ObjectId();
        var user = new ApplicationUser("name", "pass", "email", Roles.Unknown);
        var ticketToReturn = new Ticket("title", "body", user);

        //Mocking
        when(ticketStore.getTicketById(query)).thenReturn(ticketToReturn);

        //execute
        var res = container.getTicketById(query);

        //assert
        assertEquals(res, ticketToReturn);
    }

    @Test
    public void getTicketByStatusReturnsTicket() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var user = new ApplicationUser("name", "pass", "email", Roles.Unknown);
        var ticketToReturn = new Ticket("title", "body", user);

        var listOfTickets = new ArrayList<Ticket>();
        listOfTickets.add(ticketToReturn);
        //Mocking
        when(ticketStore.getTicketByStatus(TicketStatus.IN_PROGRESS)).thenReturn(listOfTickets);

        //execute
        var res = container.getTicketByStatus(TicketStatus.IN_PROGRESS);

        //assert
        assertEquals(res, listOfTickets);
    }
    @Test
    public void getTicketReturnsTickets() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var user = new ApplicationUser("name", "pass", "email", Roles.Unknown);
        var listOfTickets = new ArrayList<Ticket>();
        listOfTickets.add(new Ticket("title2", "body4", user));
        listOfTickets.add(new Ticket("title3", "body4", user));

        //Mocking
        when(ticketStore.getTickets()).thenReturn(listOfTickets);

        //execute
        var res = container.getTickets();

        //assert
        assertEquals(res, listOfTickets);
    }
    @Test
    public void getTicketByStatusAndClientReturnsTickets() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var user = new ApplicationUser("name", "pass", "email", Roles.Unknown);
        var listOfTickets = new ArrayList<Ticket>();
        listOfTickets.add(new Ticket("title2", "body4", user));
        listOfTickets.add(new Ticket("title3", "body4", user));

        //Mocking
        when(ticketStore.getTicketByStatusAndClient(TicketStatus.IN_PROGRESS, user)).thenReturn(listOfTickets);

        //execute
        var res = container.getTicketByStatusAndClient(TicketStatus.IN_PROGRESS, user);

        //assert
        assertEquals(res, listOfTickets);
    }

    @Test
    public void getTicketByCustomerReturnsTickets() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var user = new ApplicationUser("name", "pass", "email", Roles.Unknown);
        var listOfTickets = new ArrayList<Ticket>();
        listOfTickets.add(new Ticket("title2", "body4", user));
        listOfTickets.add(new Ticket("title3", "body4", user));

        //Mocking
        when(ticketStore.getTicketsByCustomer(user)).thenReturn(listOfTickets);

        //execute
        var res = container.getTicketsByCustomer(user);

        //assert
        assertEquals(res, listOfTickets);
    }
    @Test
    public void canChangeTicketStatus() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //setup
        var user = new ApplicationUser("name", "pass", "email", Roles.Unknown);
        var ticket = new Ticket("title3", "body4", user);

        assertDoesNotThrow(() -> {
            //execute
           container.changeTicketStatus(ticket, TicketStatus.IN_PROGRESS);
        });
    }


}