package com.joep.backofficeapi.DAL.Stores.Tickets;

import com.joep.backofficeapi.ConnectionConfiguration;
import com.joep.backofficeapi.DAL.Interfaces.ITicketStore;
import com.joep.backofficeapi.Models.Ticket.Ticket;
import com.joep.backofficeapi.Models.Ticket.TicketReply;
import com.joep.backofficeapi.Models.Ticket.TicketStatus;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.updates.UpdateOperators;
import org.bson.types.ObjectId;

import java.util.List;

public class MongoTicketStore implements ITicketStore {
    private final Datastore datastore;

    public MongoTicketStore() {
        datastore = Morphia.createDatastore(MongoClients.create(ConnectionConfiguration.getMongoConnectionString()), "backoffice");
        datastore.getMapper().mapPackage("org.joep.BackofficeBackend");
        datastore.ensureIndexes();
    }


    @Override
    public void addReply(Ticket ticket, TicketReply ticketReply) {
        //Standaard query om de gebruiker te vinden
        var query = datastore.find(Ticket.class)
                .filter(Filters.eq("Id", ticket.getId()));

        //Add nieuwe lijst
        query.update(UpdateOperators.addToSet("replies", ticketReply))
                .execute();
    }

    @Override
    public void addTicket(Ticket ticket) {
        datastore.save(ticket);
    }

    @Override
    public Ticket getTicketById(ObjectId id) {
       return datastore.find(Ticket.class)
                .filter(Filters.eq("Id", id))
               .first();
    }

    @Override
    public List<Ticket> getTickets() {
        return datastore.find(Ticket.class).iterator().toList();
    }

    @Override
    public List<Ticket> getPendingTickets() {
        return datastore.find(Ticket.class)
                .filter(Filters.eq("status", TicketStatus.PENDING))
                .iterator()
                .toList();

    }

    @Override
    public List<Ticket> getCompletedTickets() {
        return datastore.find(Ticket.class)
                .filter(Filters.eq("status", TicketStatus.DONE))
                .iterator()
                .toList();
    }

    @Override
    public List<Ticket> getInProgressTickets() {
        return datastore.find(Ticket.class)
                .filter(Filters.eq("status", TicketStatus.IN_PROGRESS))
                .iterator()
                .toList();
    }

    @Override
    public void changeTicketStatus(Ticket ticket, TicketStatus status) {
        var query = datastore.find(Ticket.class)
                .filter(Filters.eq("Id", ticket.getId()));
        query.update(UpdateOperators.set("status", status))
                .execute();
    }
}
